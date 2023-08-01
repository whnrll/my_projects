// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: StockMessage.proto

package com.itcast.bulls.stock.struct.netty.trade;

/**
 * <pre>
 * 用户交割单返回数据集合
 * </pre>
 *
 * Protobuf type {@code GetAccountSettlementRespDataList}
 */
public  final class GetAccountSettlementRespDataList extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:GetAccountSettlementRespDataList)
    GetAccountSettlementRespDataListOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetAccountSettlementRespDataList.newBuilder() to construct.
  private GetAccountSettlementRespDataList(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetAccountSettlementRespDataList() {
    getAccountSettlementRespData_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GetAccountSettlementRespDataList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetAccountSettlementRespDataList(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
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
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              getAccountSettlementRespData_ = new java.util.ArrayList<com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData>();
              mutable_bitField0_ |= 0x00000001;
            }
            getAccountSettlementRespData_.add(
                input.readMessage(com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData.parser(), extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        getAccountSettlementRespData_ = java.util.Collections.unmodifiableList(getAccountSettlementRespData_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.itcast.bulls.stock.struct.netty.trade.StockMessageProto.internal_static_GetAccountSettlementRespDataList_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.itcast.bulls.stock.struct.netty.trade.StockMessageProto.internal_static_GetAccountSettlementRespDataList_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList.class, com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList.Builder.class);
  }

  public static final int GETACCOUNTSETTLEMENTRESPDATA_FIELD_NUMBER = 1;
  private java.util.List<com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData> getAccountSettlementRespData_;
  /**
   * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
   */
  public java.util.List<com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData> getGetAccountSettlementRespDataList() {
    return getAccountSettlementRespData_;
  }
  /**
   * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
   */
  public java.util.List<? extends com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataOrBuilder> 
      getGetAccountSettlementRespDataOrBuilderList() {
    return getAccountSettlementRespData_;
  }
  /**
   * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
   */
  public int getGetAccountSettlementRespDataCount() {
    return getAccountSettlementRespData_.size();
  }
  /**
   * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
   */
  public com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData getGetAccountSettlementRespData(int index) {
    return getAccountSettlementRespData_.get(index);
  }
  /**
   * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
   */
  public com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataOrBuilder getGetAccountSettlementRespDataOrBuilder(
      int index) {
    return getAccountSettlementRespData_.get(index);
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
    for (int i = 0; i < getAccountSettlementRespData_.size(); i++) {
      output.writeMessage(1, getAccountSettlementRespData_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < getAccountSettlementRespData_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getAccountSettlementRespData_.get(i));
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
    if (!(obj instanceof com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList)) {
      return super.equals(obj);
    }
    com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList other = (com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList) obj;

    if (!getGetAccountSettlementRespDataList()
        .equals(other.getGetAccountSettlementRespDataList())) return false;
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
    if (getGetAccountSettlementRespDataCount() > 0) {
      hash = (37 * hash) + GETACCOUNTSETTLEMENTRESPDATA_FIELD_NUMBER;
      hash = (53 * hash) + getGetAccountSettlementRespDataList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList parseFrom(
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
  public static Builder newBuilder(com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList prototype) {
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
   * 用户交割单返回数据集合
   * </pre>
   *
   * Protobuf type {@code GetAccountSettlementRespDataList}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:GetAccountSettlementRespDataList)
      com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataListOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.itcast.bulls.stock.struct.netty.trade.StockMessageProto.internal_static_GetAccountSettlementRespDataList_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.itcast.bulls.stock.struct.netty.trade.StockMessageProto.internal_static_GetAccountSettlementRespDataList_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList.class, com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList.Builder.class);
    }

    // Construct using com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList.newBuilder()
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
        getGetAccountSettlementRespDataFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (getAccountSettlementRespDataBuilder_ == null) {
        getAccountSettlementRespData_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        getAccountSettlementRespDataBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.itcast.bulls.stock.struct.netty.trade.StockMessageProto.internal_static_GetAccountSettlementRespDataList_descriptor;
    }

    @java.lang.Override
    public com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList getDefaultInstanceForType() {
      return com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList.getDefaultInstance();
    }

    @java.lang.Override
    public com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList build() {
      com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList buildPartial() {
      com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList result = new com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList(this);
      int from_bitField0_ = bitField0_;
      if (getAccountSettlementRespDataBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          getAccountSettlementRespData_ = java.util.Collections.unmodifiableList(getAccountSettlementRespData_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.getAccountSettlementRespData_ = getAccountSettlementRespData_;
      } else {
        result.getAccountSettlementRespData_ = getAccountSettlementRespDataBuilder_.build();
      }
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
      if (other instanceof com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList) {
        return mergeFrom((com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList other) {
      if (other == com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList.getDefaultInstance()) return this;
      if (getAccountSettlementRespDataBuilder_ == null) {
        if (!other.getAccountSettlementRespData_.isEmpty()) {
          if (getAccountSettlementRespData_.isEmpty()) {
            getAccountSettlementRespData_ = other.getAccountSettlementRespData_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureGetAccountSettlementRespDataIsMutable();
            getAccountSettlementRespData_.addAll(other.getAccountSettlementRespData_);
          }
          onChanged();
        }
      } else {
        if (!other.getAccountSettlementRespData_.isEmpty()) {
          if (getAccountSettlementRespDataBuilder_.isEmpty()) {
            getAccountSettlementRespDataBuilder_.dispose();
            getAccountSettlementRespDataBuilder_ = null;
            getAccountSettlementRespData_ = other.getAccountSettlementRespData_;
            bitField0_ = (bitField0_ & ~0x00000001);
            getAccountSettlementRespDataBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getGetAccountSettlementRespDataFieldBuilder() : null;
          } else {
            getAccountSettlementRespDataBuilder_.addAllMessages(other.getAccountSettlementRespData_);
          }
        }
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
      com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData> getAccountSettlementRespData_ =
      java.util.Collections.emptyList();
    private void ensureGetAccountSettlementRespDataIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        getAccountSettlementRespData_ = new java.util.ArrayList<com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData>(getAccountSettlementRespData_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData, com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData.Builder, com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataOrBuilder> getAccountSettlementRespDataBuilder_;

    /**
     * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
     */
    public java.util.List<com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData> getGetAccountSettlementRespDataList() {
      if (getAccountSettlementRespDataBuilder_ == null) {
        return java.util.Collections.unmodifiableList(getAccountSettlementRespData_);
      } else {
        return getAccountSettlementRespDataBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
     */
    public int getGetAccountSettlementRespDataCount() {
      if (getAccountSettlementRespDataBuilder_ == null) {
        return getAccountSettlementRespData_.size();
      } else {
        return getAccountSettlementRespDataBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
     */
    public com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData getGetAccountSettlementRespData(int index) {
      if (getAccountSettlementRespDataBuilder_ == null) {
        return getAccountSettlementRespData_.get(index);
      } else {
        return getAccountSettlementRespDataBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
     */
    public Builder setGetAccountSettlementRespData(
        int index, com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData value) {
      if (getAccountSettlementRespDataBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureGetAccountSettlementRespDataIsMutable();
        getAccountSettlementRespData_.set(index, value);
        onChanged();
      } else {
        getAccountSettlementRespDataBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
     */
    public Builder setGetAccountSettlementRespData(
        int index, com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData.Builder builderForValue) {
      if (getAccountSettlementRespDataBuilder_ == null) {
        ensureGetAccountSettlementRespDataIsMutable();
        getAccountSettlementRespData_.set(index, builderForValue.build());
        onChanged();
      } else {
        getAccountSettlementRespDataBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
     */
    public Builder addGetAccountSettlementRespData(com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData value) {
      if (getAccountSettlementRespDataBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureGetAccountSettlementRespDataIsMutable();
        getAccountSettlementRespData_.add(value);
        onChanged();
      } else {
        getAccountSettlementRespDataBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
     */
    public Builder addGetAccountSettlementRespData(
        int index, com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData value) {
      if (getAccountSettlementRespDataBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureGetAccountSettlementRespDataIsMutable();
        getAccountSettlementRespData_.add(index, value);
        onChanged();
      } else {
        getAccountSettlementRespDataBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
     */
    public Builder addGetAccountSettlementRespData(
        com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData.Builder builderForValue) {
      if (getAccountSettlementRespDataBuilder_ == null) {
        ensureGetAccountSettlementRespDataIsMutable();
        getAccountSettlementRespData_.add(builderForValue.build());
        onChanged();
      } else {
        getAccountSettlementRespDataBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
     */
    public Builder addGetAccountSettlementRespData(
        int index, com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData.Builder builderForValue) {
      if (getAccountSettlementRespDataBuilder_ == null) {
        ensureGetAccountSettlementRespDataIsMutable();
        getAccountSettlementRespData_.add(index, builderForValue.build());
        onChanged();
      } else {
        getAccountSettlementRespDataBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
     */
    public Builder addAllGetAccountSettlementRespData(
        java.lang.Iterable<? extends com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData> values) {
      if (getAccountSettlementRespDataBuilder_ == null) {
        ensureGetAccountSettlementRespDataIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, getAccountSettlementRespData_);
        onChanged();
      } else {
        getAccountSettlementRespDataBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
     */
    public Builder clearGetAccountSettlementRespData() {
      if (getAccountSettlementRespDataBuilder_ == null) {
        getAccountSettlementRespData_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        getAccountSettlementRespDataBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
     */
    public Builder removeGetAccountSettlementRespData(int index) {
      if (getAccountSettlementRespDataBuilder_ == null) {
        ensureGetAccountSettlementRespDataIsMutable();
        getAccountSettlementRespData_.remove(index);
        onChanged();
      } else {
        getAccountSettlementRespDataBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
     */
    public com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData.Builder getGetAccountSettlementRespDataBuilder(
        int index) {
      return getGetAccountSettlementRespDataFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
     */
    public com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataOrBuilder getGetAccountSettlementRespDataOrBuilder(
        int index) {
      if (getAccountSettlementRespDataBuilder_ == null) {
        return getAccountSettlementRespData_.get(index);  } else {
        return getAccountSettlementRespDataBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
     */
    public java.util.List<? extends com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataOrBuilder> 
         getGetAccountSettlementRespDataOrBuilderList() {
      if (getAccountSettlementRespDataBuilder_ != null) {
        return getAccountSettlementRespDataBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(getAccountSettlementRespData_);
      }
    }
    /**
     * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
     */
    public com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData.Builder addGetAccountSettlementRespDataBuilder() {
      return getGetAccountSettlementRespDataFieldBuilder().addBuilder(
          com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData.getDefaultInstance());
    }
    /**
     * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
     */
    public com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData.Builder addGetAccountSettlementRespDataBuilder(
        int index) {
      return getGetAccountSettlementRespDataFieldBuilder().addBuilder(
          index, com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData.getDefaultInstance());
    }
    /**
     * <code>repeated .GetAccountSettlementRespData getAccountSettlementRespData = 1;</code>
     */
    public java.util.List<com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData.Builder> 
         getGetAccountSettlementRespDataBuilderList() {
      return getGetAccountSettlementRespDataFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData, com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData.Builder, com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataOrBuilder> 
        getGetAccountSettlementRespDataFieldBuilder() {
      if (getAccountSettlementRespDataBuilder_ == null) {
        getAccountSettlementRespDataBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData, com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespData.Builder, com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataOrBuilder>(
                getAccountSettlementRespData_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        getAccountSettlementRespData_ = null;
      }
      return getAccountSettlementRespDataBuilder_;
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


    // @@protoc_insertion_point(builder_scope:GetAccountSettlementRespDataList)
  }

  // @@protoc_insertion_point(class_scope:GetAccountSettlementRespDataList)
  private static final com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList();
  }

  public static com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetAccountSettlementRespDataList>
      PARSER = new com.google.protobuf.AbstractParser<GetAccountSettlementRespDataList>() {
    @java.lang.Override
    public GetAccountSettlementRespDataList parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetAccountSettlementRespDataList(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetAccountSettlementRespDataList> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetAccountSettlementRespDataList> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.itcast.bulls.stock.struct.netty.trade.GetAccountSettlementRespDataList getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

