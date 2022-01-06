package com.rjgc.Service.impl;

import com.rjgc.Service.CoachService;
import com.rjgc.entity.CoachDO;
import com.rjgc.entity.ManagerDO;
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
 * @Date: 12/31/21
 * @Time: 10:55 AM
 */
public class CoachServiceImpl implements CoachService {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    @Override
    public TableDTO retrieveCoach(Request request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from vip.coach");
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
            sql.append("select count(*) from vip.coach");
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
    public Vector<String> getCoachId() {
        Vector<String> coachId = new Vector<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select id from vip.coach");
        try{
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while(rs.next()){
                coachId.add(rs.getString("id"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return coachId;
    }

    @Override
    public Vector<Object> getCoachInfo(Object coachID) {
        Vector<Object> coachInfo = new Vector<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select name, sex, phoneNum, date, extend from vip.coach where id = ");
        sql.append(coachID.toString());
        try{
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while(rs.next()){
                coachInfo.add(rs.getString("name"));
                coachInfo.add(rs.getString("sex"));
                coachInfo.add(rs.getString("phoneNum"));
                coachInfo.add(rs.getString("date"));
                coachInfo.add(rs.getString("extend"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return coachInfo;
    }

    @Override
    public boolean addCoach(CoachDO coachDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert ignore into vip.coach(id, name, sex, phoneNum, date, extend) values (?, ?, ?, ?, ?, ?)");

        try{
            conn =DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, coachDO.getId());
            ps.setString(2, coachDO.getName());
            ps.setString(3, coachDO.getSex());
            ps.setString(4, coachDO.getPhoneNum());
            ps.setString(5, coachDO.getDate());
            ps.setString(6, coachDO.getExtend());

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
        sql.append("select id from vip.coach");
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
    public boolean updateCoach(CoachDO coachDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("update vip.coach set name = ?, sex = ?, phoneNum = ?, date = ?, extend = ? where id = ?");
        try{
            conn =DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, coachDO.getName());
            ps.setString(2, coachDO.getSex());
            ps.setString(3, coachDO.getPhoneNum());
            ps.setString(4, coachDO.getDate());
            ps.setString(5, coachDO.getExtend());
            ps.setInt(6, coachDO.getId());
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
    public boolean deleteCoachInfo(int[] selectedCoachIds) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete ignore from vip.coach where id in (");
        for (int i = 0; i < selectedCoachIds.length; i ++){
            if (i == selectedCoachIds.length - 1){
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
            for (int i = 0; i < selectedCoachIds.length; i ++){
                ps.setInt(i + 1, selectedCoachIds[i]);
            }
            return ps.executeUpdate() == selectedCoachIds.length;
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
            String extend = rs.getString("extend");
            oneRecord.addElement(id);
            oneRecord.addElement(name);
            oneRecord.addElement(sex);
            oneRecord.addElement(num);
            oneRecord.addElement(date);
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
        }
    }
}
