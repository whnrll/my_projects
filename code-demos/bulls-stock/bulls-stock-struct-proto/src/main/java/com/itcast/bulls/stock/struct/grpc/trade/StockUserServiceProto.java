// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/StockUserService.proto

package com.itcast.bulls.stock.struct.grpc.trade;

public final class StockUserServiceProto {
  private StockUserServiceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_LoginRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_LoginRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_LoginReponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_LoginReponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\033grpc/StockUserService.proto\"/\n\014LoginRe" +
      "quest\022\016\n\006userNo\030\001 \001(\t\022\017\n\007userPwd\030\002 \001(\t\"R" +
      "\n\014LoginReponse\022\016\n\006status\030\001 \001(\t\022\017\n\007messag" +
      "e\030\002 \001(\t\022\016\n\006userId\030\003 \001(\003\022\021\n\taccountId\030\004 \001" +
      "(\0032?\n\020StockUserService\022+\n\tuserLogin\022\r.Lo" +
      "ginRequest\032\r.LoginReponse\"\000BC\n(com.itcas" +
      "t.bulls.stock.struct.grpc.tradeB\025StockUs" +
      "erServiceProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_LoginRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_LoginRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_LoginRequest_descriptor,
        new java.lang.String[] { "UserNo", "UserPwd", });
    internal_static_LoginReponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_LoginReponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_LoginReponse_descriptor,
        new java.lang.String[] { "Status", "Message", "UserId", "AccountId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}