// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/StockFinanceService.proto

package com.itcast.bulls.stock.struct.grpc.trade;

public interface GetFinanceOverviewResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:GetFinanceOverviewResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string status = 1;</code>
   */
  java.lang.String getStatus();
  /**
   * <code>string status = 1;</code>
   */
  com.google.protobuf.ByteString
      getStatusBytes();

  /**
   * <code>string message = 2;</code>
   */
  java.lang.String getMessage();
  /**
   * <code>string message = 2;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();

  /**
   * <pre>
   * 总资产
   * </pre>
   *
   * <code>int64 totalAssets = 3;</code>
   */
  long getTotalAssets();

  /**
   * <pre>
   * 总市值
   * </pre>
   *
   * <code>int64 totalMarketAmount = 4;</code>
   */
  long getTotalMarketAmount();

  /**
   * <pre>
   * 持仓盈亏
   * </pre>
   *
   * <code>int64 positionProfit = 5;</code>
   */
  long getPositionProfit();

  /**
   * <pre>
   * 当日持仓盈亏
   * </pre>
   *
   * <code>int64 dayProfit = 6;</code>
   */
  long getDayProfit();

  /**
   * <pre>
   * 账户余额
   * </pre>
   *
   * <code>int64 balance = 7;</code>
   */
  long getBalance();
}
