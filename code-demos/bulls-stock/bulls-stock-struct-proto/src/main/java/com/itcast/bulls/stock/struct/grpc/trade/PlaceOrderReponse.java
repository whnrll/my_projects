// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/StockTradeDealService.proto

package com.itcast.bulls.stock.struct.grpc.trade;

/**
 * <pre>
 * The response message
 * </pre>
 *
 * Protobuf type {@code PlaceOrderReponse}
 */
public  final class PlaceOrderReponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:PlaceOrderReponse)
    PlaceOrderReponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use PlaceOrderReponse.newBuilder() to construct.
  private PlaceOrderReponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PlaceOrderReponse() {
    status_ = "";
    message_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new PlaceOrderReponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private PlaceOrderReponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            status_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            message_ = s;
            break;
          }
          case 24: {

            orderId_ = input.readInt64();
            break;
          }
          case 32: {

            dealId_ = input.readInt64();
            break;
          }
          case 40: {

            execVolume_ = input.readInt32();
            break;
          }
          case 48: {

            execPrice_ = input.readInt64();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.itcast.bulls.stock.struct.grpc.trade.StockTradeDealServiceProto.internal_static_PlaceOrderReponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.itcast.bulls.stock.struct.grpc.trade.StockTradeDealServiceProto.internal_static_PlaceOrderReponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse.class, com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse.Builder.class);
  }

  public static final int STATUS_FIELD_NUMBER = 1;
  private volatile java.lang.Object status_;
  /**
   * <code>string status = 1;</code>
   */
  public java.lang.String getStatus() {
    java.lang.Object ref = status_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      status_ = s;
      return s;
    }
  }
  /**
   * <code>string status = 1;</code>
   */
  public com.google.protobuf.ByteString
      getStatusBytes() {
    java.lang.Object ref = status_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      status_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int MESSAGE_FIELD_NUMBER = 2;
  private volatile java.lang.Object message_;
  /**
   * <code>string message = 2;</code>
   */
  public java.lang.String getMessage() {
    java.lang.Object ref = message_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      message_ = s;
      return s;
    }
  }
  /**
   * <code>string message = 2;</code>
   */
  public com.google.protobuf.ByteString
      getMessageBytes() {
    java.lang.Object ref = message_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      message_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ORDERID_FIELD_NUMBER = 3;
  private long orderId_;
  /**
   * <code>int64 orderId = 3;</code>
   */
  public long getOrderId() {
    return orderId_;
  }

  public static final int DEALID_FIELD_NUMBER = 4;
  private long dealId_;
  /**
   * <code>int64 dealId = 4;</code>
   */
  public long getDealId() {
    return dealId_;
  }

  public static final int EXECVOLUME_FIELD_NUMBER = 5;
  private int execVolume_;
  /**
   * <code>int32 execVolume = 5;</code>
   */
  public int getExecVolume() {
    return execVolume_;
  }

  public static final int EXECPRICE_FIELD_NUMBER = 6;
  private long execPrice_;
  /**
   * <code>int64 execPrice = 6;</code>
   */
  public long getExecPrice() {
    return execPrice_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getStatusBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, status_);
    }
    if (!getMessageBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, message_);
    }
    if (orderId_ != 0L) {
      output.writeInt64(3, orderId_);
    }
    if (dealId_ != 0L) {
      output.writeInt64(4, dealId_);
    }
    if (execVolume_ != 0) {
      output.writeInt32(5, execVolume_);
    }
    if (execPrice_ != 0L) {
      output.writeInt64(6, execPrice_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getStatusBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, status_);
    }
    if (!getMessageBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, message_);
    }
    if (orderId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, orderId_);
    }
    if (dealId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(4, dealId_);
    }
    if (execVolume_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, execVolume_);
    }
    if (execPrice_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(6, execPrice_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse)) {
      return super.equals(obj);
    }
    com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse other = (com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse) obj;

    if (!getStatus()
        .equals(other.getStatus())) return false;
    if (!getMessage()
        .equals(other.getMessage())) return false;
    if (getOrderId()
        != other.getOrderId()) return false;
    if (getDealId()
        != other.getDealId()) return false;
    if (getExecVolume()
        != other.getExecVolume()) return false;
    if (getExecPrice()
        != other.getExecPrice()) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + STATUS_FIELD_NUMBER;
    hash = (53 * hash) + getStatus().hashCode();
    hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
    hash = (53 * hash) + getMessage().hashCode();
    hash = (37 * hash) + ORDERID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getOrderId());
    hash = (37 * hash) + DEALID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getDealId());
    hash = (37 * hash) + EXECVOLUME_FIELD_NUMBER;
    hash = (53 * hash) + getExecVolume();
    hash = (37 * hash) + EXECPRICE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getExecPrice());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * The response message
   * </pre>
   *
   * Protobuf type {@code PlaceOrderReponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:PlaceOrderReponse)
      com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.itcast.bulls.stock.struct.grpc.trade.StockTradeDealServiceProto.internal_static_PlaceOrderReponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.itcast.bulls.stock.struct.grpc.trade.StockTradeDealServiceProto.internal_static_PlaceOrderReponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse.class, com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse.Builder.class);
    }

    // Construct using com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      status_ = "";

      message_ = "";

      orderId_ = 0L;

      dealId_ = 0L;

      execVolume_ = 0;

      execPrice_ = 0L;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.itcast.bulls.stock.struct.grpc.trade.StockTradeDealServiceProto.internal_static_PlaceOrderReponse_descriptor;
    }

    @java.lang.Override
    public com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse getDefaultInstanceForType() {
      return com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse build() {
      com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse buildPartial() {
      com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse result = new com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse(this);
      result.status_ = status_;
      result.message_ = message_;
      result.orderId_ = orderId_;
      result.dealId_ = dealId_;
      result.execVolume_ = execVolume_;
      result.execPrice_ = execPrice_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse) {
        return mergeFrom((com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse other) {
      if (other == com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse.getDefaultInstance()) return this;
      if (!other.getStatus().isEmpty()) {
        status_ = other.status_;
        onChanged();
      }
      if (!other.getMessage().isEmpty()) {
        message_ = other.message_;
        onChanged();
      }
      if (other.getOrderId() != 0L) {
        setOrderId(other.getOrderId());
      }
      if (other.getDealId() != 0L) {
        setDealId(other.getDealId());
      }
      if (other.getExecVolume() != 0) {
        setExecVolume(other.getExecVolume());
      }
      if (other.getExecPrice() != 0L) {
        setExecPrice(other.getExecPrice());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object status_ = "";
    /**
     * <code>string status = 1;</code>
     */
    public java.lang.String getStatus() {
      java.lang.Object ref = status_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        status_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string status = 1;</code>
     */
    public com.google.protobuf.ByteString
        getStatusBytes() {
      java.lang.Object ref = status_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        status_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string status = 1;</code>
     */
    public Builder setStatus(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      status_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string status = 1;</code>
     */
    public Builder clearStatus() {
      
      status_ = getDefaultInstance().getStatus();
      onChanged();
      return this;
    }
    /**
     * <code>string status = 1;</code>
     */
    public Builder setStatusBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      status_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object message_ = "";
    /**
     * <code>string message = 2;</code>
     */
    public java.lang.String getMessage() {
      java.lang.Object ref = message_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        message_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string message = 2;</code>
     */
    public com.google.protobuf.ByteString
        getMessageBytes() {
      java.lang.Object ref = message_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        message_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string message = 2;</code>
     */
    public Builder setMessage(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      message_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string message = 2;</code>
     */
    public Builder clearMessage() {
      
      message_ = getDefaultInstance().getMessage();
      onChanged();
      return this;
    }
    /**
     * <code>string message = 2;</code>
     */
    public Builder setMessageBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      message_ = value;
      onChanged();
      return this;
    }

    private long orderId_ ;
    /**
     * <code>int64 orderId = 3;</code>
     */
    public long getOrderId() {
      return orderId_;
    }
    /**
     * <code>int64 orderId = 3;</code>
     */
    public Builder setOrderId(long value) {
      
      orderId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 orderId = 3;</code>
     */
    public Builder clearOrderId() {
      
      orderId_ = 0L;
      onChanged();
      return this;
    }

    private long dealId_ ;
    /**
     * <code>int64 dealId = 4;</code>
     */
    public long getDealId() {
      return dealId_;
    }
    /**
     * <code>int64 dealId = 4;</code>
     */
    public Builder setDealId(long value) {
      
      dealId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 dealId = 4;</code>
     */
    public Builder clearDealId() {
      
      dealId_ = 0L;
      onChanged();
      return this;
    }

    private int execVolume_ ;
    /**
     * <code>int32 execVolume = 5;</code>
     */
    public int getExecVolume() {
      return execVolume_;
    }
    /**
     * <code>int32 execVolume = 5;</code>
     */
    public Builder setExecVolume(int value) {
      
      execVolume_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 execVolume = 5;</code>
     */
    public Builder clearExecVolume() {
      
      execVolume_ = 0;
      onChanged();
      return this;
    }

    private long execPrice_ ;
    /**
     * <code>int64 execPrice = 6;</code>
     */
    public long getExecPrice() {
      return execPrice_;
    }
    /**
     * <code>int64 execPrice = 6;</code>
     */
    public Builder setExecPrice(long value) {
      
      execPrice_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 execPrice = 6;</code>
     */
    public Builder clearExecPrice() {
      
      execPrice_ = 0L;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:PlaceOrderReponse)
  }

  // @@protoc_insertion_point(class_scope:PlaceOrderReponse)
  private static final com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse();
  }

  public static com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<PlaceOrderReponse>
      PARSER = new com.google.protobuf.AbstractParser<PlaceOrderReponse>() {
    @java.lang.Override
    public PlaceOrderReponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new PlaceOrderReponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PlaceOrderReponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PlaceOrderReponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.itcast.bulls.stock.struct.grpc.trade.PlaceOrderReponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
