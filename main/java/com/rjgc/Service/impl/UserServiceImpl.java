package com.rjgc.Service.impl;

import com.rjgc.Service.UsersService;
import com.rjgc.entity.UserDO;
import com.rjgc.request.Request;
import com.rjgc.res.TableDTO;
import com.rjgc.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/28/21
 * @Time: 8:33 PM
 */
public class UserServiceImpl implements UsersService {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    @Override
    public TableDTO retrieveUser(Request request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from vip.user");
        extracted(request, sql);
        sql.append(" order by date asc limit ").append(request.getStart()).append(",").append(request.getPageSize());
        TableDTO tableDTO = new TableDTO();

        try{
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            // 设置数据
            tableDTO.setData(fillData(rs));

            sql.setLength(0);
            sql.append("select count(*) from vip.user");
            extracted(request, sql);
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while(rs.next()){
                // 设置数据数量
                int count = rs.getInt(1);
                tableDTO.setTotalCount(count);
            }
            return tableDTO;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }

    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();
        while(rs.next()){
            // 处理查出的每一条记录
            Vector<Object> oneRecord = new Vector<>();
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String sex = rs.getString("sex");
            String num = rs.getString("phoneNum");
            String date = rs.getString("date");
            String level = rs.getString("level");
            String days = rs.getString("days");
            int coachID = rs.getInt("coachId");
            String extend = rs.getString("extend");
            oneRecord.addElement(id);
            oneRecord.addElement(name);
            oneRecord.addElement(sex);
            oneRecord.addElement(num);
            oneRecord.addElement(date);
            oneRecord.addElement(level);
            oneRecord.addElement(days);
            oneRecord.addElement(coachID);
            oneRecord.addElement(extend);
            data.addElement(oneRecord);
        }
        return data;
    }

    private void extracted(Request request, StringBuilder sql) {
        if(request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())){
            sql.append(" where name like '%").append(request.getSearchKey().trim()).append("%'");
            sql.append(" or id = '").append( request.getSearchKey().trim()).append("'");
            sql.append(" or sex = '").append( request.getSearchKey().trim()).append("'");
            sql.append(" or phoneNum like '%").append(request.getSearchKey().trim()).append("%'");
            sql.append(" or coachId = '").append(request.getSearchKey().trim()).append("'");

        }
    }

    // 获取当前的所有会员编号
    @Override
    public Vector<String> getUserId() {
        Vector<String> userId = new Vector<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select id from vip.user");
        try{
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while(rs.next()){
                userId.add(rs.getString("id"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return userId;
    }
    // 获取选中用户信息
    @Override
    public Vector<Object> getUserInfo(Object userID) {
        Vector<Object> userInfo = new Vector<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select name, sex, phoneNum, level, days, coachId, extend from vip.user where id = ");
        sql.append(userID.toString());
        try{
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while(rs.next()){
                userInfo.add(rs.getString("name"));
                userInfo.add(rs.getString("sex"));
                userInfo.add(rs.getString("phoneNum"));
                userInfo.add(rs.getString("level"));
                userInfo.add(rs.getString("days"));
                userInfo.add(rs.getString("coachId"));
                userInfo.add(rs.getString("extend"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return userInfo;
    }
    // 添加用户
    @Override
    public boolean addUser(UserDO userDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert ignore into vip.user(id, name, sex, phoneNum, date, level, days, coachId, extend) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");

        try{
            conn =DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, userDO.getId());
            ps.setString(2, userDO.getName());
            ps.setString(3, userDO.getSex());
            ps.setString(4, userDO.getPhoneNum());
            ps.setString(5, userDO.getDate());
            ps.setString(6, userDO.getLevel());
            ps.setString(7, userDO.getDays());
            ps.setInt(8, userDO.getCoachId());
            ps.setString(9, userDO.getExtend());
            return ps.executeUpdate() == 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }
    // 判断Id是否存在
    @Override
    public boolean judgeId(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("select id from vip.user");
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while(rs.next()){
                if(id == rs.getInt(1)){
                    return true;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

    @Override
    public boolean updateUser(UserDO userDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("update vip.user set name = ?, sex = ?, phoneNum = ?, level = ?, days = ?, coachId = ?, extend = ? where id = ?");
        try{
            conn =DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, userDO.getName());
            ps.setString(2, userDO.getSex());
            ps.setString(3, userDO.getPhoneNum());
            ps.setString(4, userDO.getLevel());
            ps.setString(5, userDO.getDays());
            ps.setInt(6, userDO.getCoachId());
            ps.setString(7, userDO.getExtend());
            ps.setInt(8, userDO.getId());
            return ps.executeUpdate() == 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

    @Override
    public boolean deleteUserInfo(int[] selectedUserIds) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete ignore from vip.user where id in (");
        for (int i = 0; i < selectedUserIds.length; i ++){
            if (i == selectedUserIds.length - 1){
                sql.append("?");
            }
            else{
                sql.append("?, ");
            }
        }
        sql.append(" )");
        try{
            conn =DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            for (int i = 0; i < selectedUserIds.length; i ++){
                ps.setInt(i + 1, selectedUserIds[i]);
            }
            return ps.executeUpdate() == selectedUserIds.length;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }
}
