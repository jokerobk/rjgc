package com.rjgc.Service.impl;

import com.rjgc.Service.ManagerService;
import com.rjgc.entity.ManagerDO;
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
 * @Date: 12/30/21
 * @Time: 10:13 PM
 */
public class ManagerServiceImpl implements ManagerService {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    @Override
    public TableDTO retrieveManager(Request request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from vip.manager ");
        extracted(request, sql);
        sql.append(" order by id asc limit ").append(request.getStart()).append(",").append(request.getPageSize());
        TableDTO tableDTO = new TableDTO();

        try{
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            // 设置数据
            tableDTO.setData(fillData(rs));

            sql.setLength(0);
            sql.append("select count(*) from vip.manager ");
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

    @Override
    public Vector<String> getManagerId() {
        Vector<String> managerId = new Vector<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select id from vip.manager");
        try{
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while(rs.next()){
                managerId.add(rs.getString("id"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return managerId;
    }

    @Override
    public Vector<Object> getManagerInfo(Object managerID) {
        Vector<Object> managerInfo = new Vector<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select userName, pwd, holderId, holderName from vip.manager where id = ");
        sql.append(managerID.toString());
        try{
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while(rs.next()){
                managerInfo.add(rs.getString("userName"));
                managerInfo.add(rs.getString("pwd"));
                managerInfo.add(rs.getInt("holderId"));
                managerInfo.add(rs.getString("holderName"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return managerInfo;
    }

    @Override
    public boolean addManager(ManagerDO managerDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert ignore into vip.manager(userName, pwd, holderId, holderName) values (?, ?, ?, ?)");

        try{
            conn =DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            
            ps.setString(1, managerDO.getManagerName());
            ps.setString(2, managerDO.getManagerPwd());
            ps.setInt(3, managerDO.getHolderId());
            ps.setString(4, managerDO.getHolderName());

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
    public boolean judgeId(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("select id from vip.manager");
        try{
            conn = DBUtil.getConn();
            assert conn != null;
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
    public boolean updateManager(ManagerDO managerDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("update vip.manager set userName = ?, pwd = ?, holderId = ?, holderName = ? where id = ?");
        try{
            conn =DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, managerDO.getManagerName());
            ps.setString(2, managerDO.getManagerPwd());
            ps.setInt(3, managerDO.getHolderId());
            ps.setString(4, managerDO.getHolderName());
            ps.setInt(5, managerDO.getId());

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
    public boolean deleteManagerInfo(int[] selectedManagerIds) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete ignore from vip.manager where id in (");
        for (int i = 0; i < selectedManagerIds.length; i ++){
            if (i == selectedManagerIds.length - 1){
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
            for (int i = 0; i < selectedManagerIds.length; i ++){
                ps.setInt(i + 1, selectedManagerIds[i]);
            }
            return ps.executeUpdate() == selectedManagerIds.length;
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

    private void extracted(Request request, StringBuilder sql) {
        if(request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())){
            sql.append("where id like '%").append(request.getSearchKey().trim()).append("%' ");
            sql.append("or userName like '%").append(request.getSearchKey().trim()).append("%' ");

        }
    }
    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();
        while(rs.next()){
            // 处理查出的每一条记录
            Vector<Object> oneRecord = new Vector<>();
            int id = rs.getInt("id");
            String userName = rs.getString("userName");
            String pwd = rs.getString("pwd");
            int holderId = rs.getInt("holderId");
            String holderName = rs.getString("holderName");
            oneRecord.addElement(id);
            oneRecord.addElement(userName);
            oneRecord.addElement(pwd);
            oneRecord.addElement(holderId);
            oneRecord.addElement(holderName);
            data.addElement(oneRecord);
        }
        return data;
    }
}
