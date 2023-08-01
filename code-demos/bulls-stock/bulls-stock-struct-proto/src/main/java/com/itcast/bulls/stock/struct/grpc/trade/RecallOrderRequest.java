// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/StockPendingService.proto

package com.itcast.bulls.stock.struct.grpc.trade;

/**
 * <pre>
 * 撤单请求的数据结构
 * </pre>
 *
 * Protobuf type {@code RecallOrderRequest}
 */
public  final class RecallOrderRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:RecallOrderRequest)
    RecallOrderRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use RecallOrderRequest.newBuilder() to construct.
  private RecallOrderRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RecallOrderRequest() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new RecallOrderRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private RecallOrderRequest(
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
          case 8: {

            accountId_ = input.readInt64();
            break;
          }
          case 16: {

            orderId_ = input.readInt64();
            break;
          }
          case 24: {

            stockId_ = input.readInt64();
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
    return com.itcast.bulls.stock.struct.grpc.trade.StockPendingServiceProto.internal_static_RecallOrderRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.itcast.bulls.stock.struct.grpc.trade.StockPendingServiceProto.internal_static_RecallOrderRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest.class, com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest.Builder.class);
  }

  public static final int ACCOUNTID_FIELD_NUMBER = 1;
  private long accountId_;
  /**
   * <pre>
   * 账户ID
   * </pre>
   *
   * <code>int64 accountId = 1;</code>
   */
  public long getAccountId() {
    return accountId_;
  }

  public static final int ORDERID_FIELD_NUMBER = 2;
  private long orderId_;
  /**
   * <pre>
   * 订单ID
   * </pre>
   *
   * <code>int64 orderId = 2;</code>
   */
  public long getOrderId() {
    return orderId_;
  }

  public static final int STOCKID_FIELD_NUMBER = 3;
  private long stockId_;
  /**
   * <pre>
   * 股票产品ID
   * </pre>
   *
   * <code>int64 stockId = 3;</code>
   */
  public long getStockId() {
    return stockId_;
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
    if (accountId_ != 0L) {
      output.writeInt64(1, accountId_);
    }
    if (orderId_ != 0L) {
      output.writeInt64(2, orderId_);
    }
    if (stockId_ != 0L) {
      output.writeInt64(3, stockId_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (accountId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, accountId_);
    }
    if (orderId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, orderId_);
    }
    if (stockId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, stockId_);
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
    if (!(obj instanceof com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest)) {
      return super.equals(obj);
    }
    com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest other = (com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest) obj;

    if (getAccountId()
        != other.getAccountId()) return false;
    if (getOrderId()
        != other.getOrderId()) return false;
    if (getStockId()
        != other.getStockId()) return false;
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
    hash = (37 * hash) + ACCOUNTID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getAccountId());
    hash = (37 * hash) + ORDERID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getOrderId());
    hash = (37 * hash) + STOCKID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getStockId());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest parseFrom(
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
  public static Builder newBuilder(com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest prototype) {
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
   * 撤单请求的数据结构
   * </pre>
   *
   * Protobuf type {@code RecallOrderRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:RecallOrderRequest)
      com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.itcast.bulls.stock.struct.grpc.trade.StockPendingServiceProto.internal_static_RecallOrderRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.itcast.bulls.stock.struct.grpc.trade.StockPendingServiceProto.internal_static_RecallOrderRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest.class, com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest.Builder.class);
    }

    // Construct using com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest.newBuilder()
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
      accountId_ = 0L;

      orderId_ = 0L;

      stockId_ = 0L;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.itcast.bulls.stock.struct.grpc.trade.StockPendingServiceProto.internal_static_RecallOrderRequest_descriptor;
    }

    @java.lang.Override
    public com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest getDefaultInstanceForType() {
      return com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest build() {
      com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest buildPartial() {
      com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest result = new com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest(this);
      result.accountId_ = accountId_;
      result.orderId_ = orderId_;
      result.stockId_ = stockId_;
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
      if (other instanceof com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest) {
        return mergeFrom((com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest other) {
      if (other == com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest.getDefaultInstance()) return this;
      if (other.getAccountId() != 0L) {
        setAccountId(other.getAccountId());
      }
      if (other.getOrderId() != 0L) {
        setOrderId(other.getOrderId());
      }
      if (other.getStockId() != 0L) {
        setStockId(other.getStockId());
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
      com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long accountId_ ;
    /**
     * <pre>
     * 账户ID
     * </pre>
     *
     * <code>int64 accountId = 1;</code>
     */
    public long getAccountId() {
      return accountId_;
    }
    /**
     * <pre>
     * 账户ID
     * </pre>
     *
     * <code>int64 accountId = 1;</code>
     */
    public Builder setAccountId(long value) {
      
      accountId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 账户ID
     * </pre>
     *
     * <code>int64 accountId = 1;</code>
     */
    public Builder clearAccountId() {
      
      accountId_ = 0L;
      onChanged();
      return this;
    }

    private long orderId_ ;
    /**
     * <pre>
     * 订单ID
     * </pre>
     *
     * <code>int64 orderId = 2;</code>
     */
    public long getOrderId() {
      return orderId_;
    }
    /**
     * <pre>
     * 订单ID
     * </pre>
     *
     * <code>int64 orderId = 2;</code>
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
     * <code>int64 orderId = 2;</code>
     */
    public Builder clearOrderId() {
      
      orderId_ = 0L;
      onChanged();
      return this;
    }

    private long stockId_ ;
    /**
     * <pre>
     * 股票产品ID
     * </pre>
     *
     * <code>int64 stockId = 3;</code>
     */
    public long getStockId() {
      return stockId_;
    }
    /**
     * <pre>
     * 股票产品ID
     * </pre>
     *
     * <code>int64 stockId = 3;</code>
     */
    public Builder setStockId(long value) {
      
      stockId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 股票产品ID
     * </pre>
     *
     * <code>int64 stockId = 3;</code>
     */
    public Builder clearStockId() {
      
      stockId_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:RecallOrderRequest)
  }

  // @@protoc_insertion_point(class_scope:RecallOrderRequest)
  private static final com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest();
  }

  public static com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RecallOrderRequest>
      PARSER = new com.google.protobuf.AbstractParser<RecallOrderRequest>() {
    @java.lang.Override
    public RecallOrderRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new RecallOrderRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RecallOrderRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RecallOrderRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.itcast.bulls.stock.struct.grpc.trade.RecallOrderRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

