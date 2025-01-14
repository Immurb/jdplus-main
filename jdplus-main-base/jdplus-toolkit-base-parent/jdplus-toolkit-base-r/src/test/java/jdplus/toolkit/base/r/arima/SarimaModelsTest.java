/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdplus.toolkit.base.r.arima;

import tck.demetra.data.Data;
import jdplus.toolkit.base.core.regarima.RegArimaEstimation;
import jdplus.toolkit.base.core.sarima.SarimaModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author palatej
 */
public class SarimaModelsTest {

    public SarimaModelsTest() {
    }

    @Test
    public void testRandom() {
        double[] rnd = SarimaModels.random(200, 12, new double[]{-.2, -.5}, 1, new double[]{-.5}, null, 1, new double[]{-.8}, 1, 5);
//        System.out.println(DoubleSeq.of(rnd));
        assertTrue(rnd.length == 200);
    }

    @Test
    public void testArima() {
        RegArimaEstimation<SarimaModel> estimate = SarimaModels.estimate(Data.PROD, new int[]{0, 1, 1}, 12, new int[]{0, 1, 1}, false, null, null, 1e-9);
        assertTrue(estimate != null);
        byte[] buffer = SarimaModels.toBuffer(estimate);
        assertTrue(buffer != null);
    }

}
