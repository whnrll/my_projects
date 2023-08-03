// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/StockTradeDealService.proto

package com.itcast.bulls.stock.struct.grpc.trade;

public interface PlaceOrderRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:PlaceOrderRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 accountId = 1;</code>
   */
  long getAccountId();

  /**
   * <code>int64 stockId = 2;</code>
   */
  long getStockId();

  /**
   * <code>int32 direction = 3;</code>
   */
  int getDirection();

  /**
   * <code>int32 type = 4;</code>
   */
  int getType();

  /**
   * <code>int32 execVolume = 5;</code>
   */
  int getExecVolume();

  /**
   * <code>int64 execPrice = 6;</code>
   */
  long getExecPrice();

  /**
   * <code>string xid = 7;</code>
   */
  java.lang.String getXid();
  /**
   * <code>string xid = 7;</code>
   */
  com.google.protobuf.ByteString
      getXidBytes();
}