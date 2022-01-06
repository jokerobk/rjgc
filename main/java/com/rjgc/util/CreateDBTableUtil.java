package com.rjgc.util;


import java.sql.*;

/**
 * Author: zhangxiaofeng
 * Date: 2021/12/31
 * Time: 21:27
 */
public class CreateDBTableUtil {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public CreateDBTableUtil(){
        final String databaseName = "vip";
        final String userTable = "user";
        final String managerTable = "manager";
        final String coachTable = "coach";
        final String equipmentTable = "equipment";
        createDatabase(databaseName);
        createCoachTable(databaseName, coachTable);
        createEquipmentTable(databaseName, managerTable);
        createManagerTable(databaseName, managerTable);
        createUserTable(databaseName, userTable);
    }
    // 创建数据库
    public void createDatabase(String databaseName){
        if (!databaseExist(databaseName)){
            String sql = "CREATE DATABASE " + databaseName;
            linkSql(sql);
        }
    }
    // 创建教练表
    public void createCoachTable(String databaseName, String tableName){
        if(!validateTableExist(databaseName, tableName)){
            String sql = "create table " + databaseName + ".coach\n" +
                    "(\n" +
                    "    id       int auto_increment\n" +
                    "        primary key,\n" +
                    "    name     varchar(10)  null,\n" +
                    "    sex      varchar(10)  null,\n" +
                    "    phoneNum varchar(20)  null,\n" +
                    "    date     date         null,\n" +
                    "    extend   varchar(100) null\n" +
                    ")";
            linkSql(sql);
        }
    }
    // 创建器材表
    public void createEquipmentTable(String databaseName, String tableName){
        if(!validateTableExist(databaseName, tableName)){
            String sql = "create table " + databaseName + ".equipment\n" +
                    "(\n" +
                    "    id         int         not null\n" +
                    "        primary key,\n" +
                    "    name       varchar(20) null,\n" +
                    "    date       date        null,\n" +
                    "    company    varchar(50) null,\n" +
                    "    num        int         null,\n" +
                    "    price      int         null,\n" +
                    "    totalPrice bigint      null\n" +
                    ")\n";
            linkSql(sql);
        }
    }
    // 创建管理员账号表
    public void createManagerTable(String databaseName, String tableName){
        if(!validateTableExist(databaseName, tableName)){
            String sql = "create table "+ databaseName + ".manager\n" +
                    "(\n" +
                    "    id         int auto_increment\n" +
                    "        primary key,\n" +
                    "    userName   varchar(50) null,\n" +
                    "    pwd        varchar(50) null,\n" +
                    "    holderId   int         null,\n" +
                    "    holderName varchar(10) null,\n" +
                    "    constraint manager_userName_uindex\n" +
                    "        unique (userName)\n" +
                    ")";
            linkSql(sql);
            sql = "INSERT INTO " + databaseName + "." + tableName + " VALUES(0, 'admin', 'admin', 0, 'BOSS')";
            linkSql(sql);
        }
    }
    // 创建会员用户表
    public void createUserTable(String databaseName, String tableName){

        if(!validateTableExist(databaseName, tableName)){
            String sql = "create table " + databaseName + ".user\n" +
                    "(\n" +
                    "    id       int auto_increment\n" +
                    "        primary key,\n" +
                    "    name     varchar(10)  null,\n" +
                    "    sex      varchar(10)  null,\n" +
                    "    phoneNum varchar(20)  null,\n" +
                    "    date     varchar(20)  null,\n" +
                    "    level    varchar(20)  null,\n" +
                    "    days     varchar(20)  null,\n" +
                    "    coachId  int          null,\n" +
                    "    extend   varchar(100) null,\n" +
                    "    constraint user_ibfk_1\n" +
                    "        foreign key (coachId) references coach (id)\n" +
                    ")";
            linkSql(sql);
        }
    }
    // 连接访问数据库
    private void linkSql(String sql) {
        try{
            conn = DBUtil.getConn();
            assert conn != null;
            st = conn.createStatement();
            int i = st.executeUpdate(sql);
        }catch (Exception e){
            System.out.print("");
        }finally{
            DBUtil.closeRs(rs);
            DBUtil.closeSt(st);
            DBUtil.closeConn(conn);
        }
    }

    //  判断数据库是否存在
    public boolean databaseExist(String databaseName) {
        String sql = "SELECT database_name FROM mysql.innodb_table_stats WHERE database_name = " + databaseName;

        conn = DBUtil.getConn();
        try{
            st = conn.createStatement();
            rs = st.executeQuery(sql);
        }catch (Exception e){
            return false;
        }finally{
            DBUtil.closeRs(rs);
            DBUtil.closeSt(st);
            DBUtil.closeConn(conn);
        }
        return true;
    }

    // 判断表是否存在
    public boolean validateTableExist(String tableName, String databaseName){

        //一个查询该表所有的语句。
        String sql = "SELECT IGNORE COUNT(*) FROM "+ databaseName + "." + tableName ;
        //获取连接
        conn = DBUtil.getConn();
        try{
            assert conn != null;
            st = conn.createStatement();
            rs = st.executeQuery(sql);

        }catch(Exception e){
            //该表不存在，则 会跳入catch中
            return false;
        }finally{
            //关闭所有连接
            DBUtil.closeRs(rs);
            DBUtil.closeSt(st);
            DBUtil.closeConn(conn);
        }
        return true;

    }

}
