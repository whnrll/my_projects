// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/StockPendingService.proto

package com.itcast.bulls.stock.struct.grpc.trade;

public interface LimitPlaceOrderReponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:LimitPlaceOrderReponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 处理状态
   * </pre>
   *
   * <code>string status = 1;</code>
   */
  java.lang.String getStatus();
  /**
   * <pre>
   * 处理状态
   * </pre>
   *
   * <code>string status = 1;</code>
   */
  com.google.protobuf.ByteString
      getStatusBytes();

  /**
   * <pre>
   * 返回消息
   * </pre>
   *
   * <code>string message = 2;</code>
   */
  java.lang.String getMessage();
  /**
   * <pre>
   * 返回消息
   * </pre>
   *
   * <code>string message = 2;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();

  /**
   * <pre>
   * 订单ID
   * </pre>
   *
   * <code>int64 orderId = 3;</code>
   */
  long getOrderId();
}