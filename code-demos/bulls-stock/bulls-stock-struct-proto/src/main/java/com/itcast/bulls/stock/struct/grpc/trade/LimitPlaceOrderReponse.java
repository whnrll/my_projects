// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/StockPendingService.proto

package com.itcast.bulls.stock.struct.grpc.trade;

/**
 * <pre>
 * 限价委托的返回数据包结构
 * </pre>
 *
 * Protobuf type {@code LimitPlaceOrderReponse}
 */
public  final class LimitPlaceOrderReponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:LimitPlaceOrderReponse)
    LimitPlaceOrderReponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use LimitPlaceOrderReponse.newBuilder() to construct.
  private LimitPlaceOrderReponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private LimitPlaceOrderReponse() {
    status_ = "";
    message_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new LimitPlaceOrderReponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private LimitPlaceOrderReponse(
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
    return com.itcast.bulls.stock.struct.grpc.trade.StockPendingServiceProto.internal_static_LimitPlaceOrderReponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.itcast.bulls.stock.struct.grpc.trade.StockPendingServiceProto.internal_static_LimitPlaceOrderReponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse.class, com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse.Builder.class);
  }

  public static final int STATUS_FIELD_NUMBER = 1;
  private volatile java.lang.Object status_;
  /**
   * <pre>
   * 处理状态
   * </pre>
   *
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
   * <pre>
   * 处理状态
   * </pre>
   *
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
   * <pre>
   * 返回消息
   * </pre>
   *
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
   * <pre>
   * 返回消息
   * </pre>
   *
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
   * <pre>
   * 订单ID
   * </pre>
   *
   * <code>int64 orderId = 3;</code>
   */
  public long getOrderId() {
    return orderId_;
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
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse)) {
      return super.equals(obj);
    }
    com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse other = (com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse) obj;

    if (!getStatus()
        .equals(other.getStatus())) return false;
    if (!getMessage()
        .equals(other.getMessage())) return false;
    if (getOrderId()
        != other.getOrderId()) return false;
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
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse parseFrom(
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
  public static Builder newBuilder(com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse prototype) {
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
   * 限价委托的返回数据包结构
   * </pre>
   *
   * Protobuf type {@code LimitPlaceOrderReponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:LimitPlaceOrderReponse)
      com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.itcast.bulls.stock.struct.grpc.trade.StockPendingServiceProto.internal_static_LimitPlaceOrderReponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.itcast.bulls.stock.struct.grpc.trade.StockPendingServiceProto.internal_static_LimitPlaceOrderReponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse.class, com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse.Builder.class);
    }

    // Construct using com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse.newBuilder()
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

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.itcast.bulls.stock.struct.grpc.trade.StockPendingServiceProto.internal_static_LimitPlaceOrderReponse_descriptor;
    }

    @java.lang.Override
    public com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse getDefaultInstanceForType() {
      return com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse build() {
      com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse buildPartial() {
      com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse result = new com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse(this);
      result.status_ = status_;
      result.message_ = message_;
      result.orderId_ = orderId_;
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
      if (other instanceof com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse) {
        return mergeFrom((com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse other) {
      if (other == com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse.getDefaultInstance()) return this;
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
      com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse) e.getUnfinishedMessage();
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
     * <pre>
     * 处理状态
     * </pre>
     *
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
     * <pre>
     * 处理状态
     * </pre>
     *
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
     * <pre>
     * 处理状态
     * </pre>
     *
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
     * <pre>
     * 处理状态
     * </pre>
     *
     * <code>string status = 1;</code>
     */
    public Builder clearStatus() {
      
      status_ = getDefaultInstance().getStatus();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 处理状态
     * </pre>
     *
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
     * <pre>
     * 返回消息
     * </pre>
     *
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
     * <pre>
     * 返回消息
     * </pre>
     *
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
     * <pre>
     * 返回消息
     * </pre>
     *
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
     * <pre>
     * 返回消息
     * </pre>
     *
     * <code>string message = 2;</code>
     */
    public Builder clearMessage() {
      
      message_ = getDefaultInstance().getMessage();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 返回消息
     * </pre>
     *
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
     * <pre>
     * 订单ID
     * </pre>
     *
     * <code>int64 orderId = 3;</code>
     */
    public long getOrderId() {
      return orderId_;
    }
    /**
     * <pre>
     * 订单ID
     * </pre>
     *
     * <code>int64 orderId = 3;</code>
     */
    public Builder setOrderId(long value) {
      
      orderId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 订单ID
     * </pre>
     *
     * <code>int64 orderId = 3;</code>
     */
    public Builder clearOrderId() {
      
      orderId_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:LimitPlaceOrderReponse)
  }

  // @@protoc_insertion_point(class_scope:LimitPlaceOrderReponse)
  private static final com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse();
  }

  public static com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<LimitPlaceOrderReponse>
      PARSER = new com.google.protobuf.AbstractParser<LimitPlaceOrderReponse>() {
    @java.lang.Override
    public LimitPlaceOrderReponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new LimitPlaceOrderReponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<LimitPlaceOrderReponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<LimitPlaceOrderReponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.itcast.bulls.stock.struct.grpc.trade.LimitPlaceOrderReponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
