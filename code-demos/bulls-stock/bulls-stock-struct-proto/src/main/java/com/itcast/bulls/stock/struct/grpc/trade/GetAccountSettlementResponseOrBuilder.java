// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/StockFinanceService.proto

package com.itcast.bulls.stock.struct.grpc.trade;

public interface GetAccountSettlementResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:GetAccountSettlementResponse)
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
   * <code>repeated .GetAccountSettlementSingle getAccountSettlementSingle = 3;</code>
   */
  java.util.List<com.itcast.bulls.stock.struct.grpc.trade.GetAccountSettlementSingle> 
      getGetAccountSettlementSingleList();
  /**
   * <code>repeated .GetAccountSettlementSingle getAccountSettlementSingle = 3;</code>
   */
  com.itcast.bulls.stock.struct.grpc.trade.GetAccountSettlementSingle getGetAccountSettlementSingle(int index);
  /**
   * <code>repeated .GetAccountSettlementSingle getAccountSettlementSingle = 3;</code>
   */
  int getGetAccountSettlementSingleCount();
  /**
   * <code>repeated .GetAccountSettlementSingle getAccountSettlementSingle = 3;</code>
   */
  java.util.List<? extends com.itcast.bulls.stock.struct.grpc.trade.GetAccountSettlementSingleOrBuilder> 
      getGetAccountSettlementSingleOrBuilderList();
  /**
   * <code>repeated .GetAccountSettlementSingle getAccountSettlementSingle = 3;</code>
   */
  com.itcast.bulls.stock.struct.grpc.trade.GetAccountSettlementSingleOrBuilder getGetAccountSettlementSingleOrBuilder(
      int index);
}
