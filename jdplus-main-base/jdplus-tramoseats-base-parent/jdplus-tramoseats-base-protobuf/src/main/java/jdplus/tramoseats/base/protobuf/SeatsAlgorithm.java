// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tramoseats.proto

package jdplus.tramoseats.base.protobuf;

/**
 * Protobuf enum {@code tramoseats.SeatsAlgorithm}
 */
public enum SeatsAlgorithm
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>SEATS_ALG_BURMAN = 0;</code>
   */
  SEATS_ALG_BURMAN(0),
  /**
   * <code>SEATS_ALG_KALMANSMOOTHER = 1;</code>
   */
  SEATS_ALG_KALMANSMOOTHER(1),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>SEATS_ALG_BURMAN = 0;</code>
   */
  public static final int SEATS_ALG_BURMAN_VALUE = 0;
  /**
   * <code>SEATS_ALG_KALMANSMOOTHER = 1;</code>
   */
  public static final int SEATS_ALG_KALMANSMOOTHER_VALUE = 1;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static SeatsAlgorithm valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static SeatsAlgorithm forNumber(int value) {
    switch (value) {
      case 0: return SEATS_ALG_BURMAN;
      case 1: return SEATS_ALG_KALMANSMOOTHER;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<SeatsAlgorithm>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      SeatsAlgorithm> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<SeatsAlgorithm>() {
          public SeatsAlgorithm findValueByNumber(int number) {
            return SeatsAlgorithm.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalStateException(
          "Can't get the descriptor of an unrecognized enum value.");
    }
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return TramoSeatsProtos.getDescriptor().getEnumTypes().get(1);
  }

  private static final SeatsAlgorithm[] VALUES = values();

  public static SeatsAlgorithm valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private SeatsAlgorithm(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:tramoseats.SeatsAlgorithm)
}
