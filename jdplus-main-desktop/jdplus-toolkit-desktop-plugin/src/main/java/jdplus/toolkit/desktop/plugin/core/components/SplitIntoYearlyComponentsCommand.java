package jdplus.toolkit.desktop.plugin.core.components;

import jdplus.toolkit.base.api.data.Range;
import jdplus.toolkit.desktop.plugin.components.ComponentCommand;
import jdplus.toolkit.desktop.plugin.components.TsSelectionBridge;
import jdplus.toolkit.desktop.plugin.components.parts.HasTsCollection;
import jdplus.toolkit.desktop.plugin.tsproviders.DataSourceManager;
import jdplus.toolkit.base.api.timeseries.*;
import jdplus.toolkit.base.tsp.util.ObsFormat;
import jdplus.toolkit.desktop.plugin.core.tools.JTsChartTopComponent;
import ec.util.list.swing.JLists;

import java.beans.BeanInfo;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.OptionalInt;

public final class SplitIntoYearlyComponentsCommand extends ComponentCommand<HasTsCollection> {

    public static final SplitIntoYearlyComponentsCommand INSTANCE = new SplitIntoYearlyComponentsCommand();

    private SplitIntoYearlyComponentsCommand() {
        super(TsSelectionBridge.TS_SELECTION_PROPERTY);
    }

    @Override
    public boolean isEnabled(HasTsCollection c) {
        OptionalInt selection = JLists.getSelectionIndexStream(c.getTsSelectionModel()).findFirst();
        if (selection.isPresent()) {
            TsData data = c.getTsCollection().get(selection.getAsInt()).getData();
            return !data.isEmpty() && Duration.between(data.getDomain().start(), data.getDomain().end()).toDays() > 365;
        }
        return false;
    }

    @Override
    public void execute(HasTsCollection component) throws Exception {
        Ts ts = (component.getTsCollection().get(component.getTsSelectionModel().getMinSelectionIndex()));
        JTsChartTopComponent c = new JTsChartTopComponent();
        c.getChart().setTitle(ts.getName());
        c.getChart().setObsFormat(ObsFormat.builder().locale(null).dateTimePattern("MMM").build());
        c.getChart().setTsUpdateMode(HasTsCollection.TsUpdateMode.None);
        c.getChart().setTsCollection(split(ts));
        c.setIcon(DataSourceManager.get().getImage(ts.getMoniker(), BeanInfo.ICON_COLOR_16x16, false));
        c.open();
        c.requestActive();
    }

    private static TsDomain yearsOf(TsDomain domain) {
        return domain.aggregate(TsUnit.YEAR, false);
    }

    private static Ts dataOf(Range<LocalDateTime> year, TsData data) {
        TsData select = data.select(TimeSelector.between(year));
        TsData result = withYear(2000, select);
        return Ts.builder().moniker(TsMoniker.of()).data(result).name(year.start().getYear() + "").build();
    }

    private static TsData withYear(int year, TsData data) {
        return TsData.of(withYear(year, data.getStart()), data.getValues());
    }

    private static TsPeriod withYear(int year, TsPeriod start) {
        return start.withDate(start.start().withYear(year));
    }

    private static TsCollection split(Ts ts) {
        return yearsOf(ts.getData().getDomain())
                .stream()
                .map(year -> dataOf(year, ts.getData()))
                .collect(TsCollection.toTsCollection());
    }
}
