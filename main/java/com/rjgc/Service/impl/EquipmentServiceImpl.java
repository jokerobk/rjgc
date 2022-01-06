package com.rjgc.Service.impl;

import com.rjgc.Service.EquipmentService;
import com.rjgc.entity.EquipmentDO;
import com.rjgc.request.Request;
import com.rjgc.res.TableDTO;
import com.rjgc.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Author: zhangxiaofeng
 * Date: 2021/12/31
 * Time: 19:20
 */
public class EquipmentServiceImpl implements EquipmentService {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    @Override
    public TableDTO retrieveEquipment(Request request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from vip.equipment ");
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
            sql.append("select count(*) from vip.equipment ");
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
    public Vector<String> getEquipmentId() {
        Vector<String> equipmentId = new Vector<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select id from vip.equipment");
        try{
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while(rs.next()){
                equipmentId.add(rs.getString("id"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return equipmentId;
    }

    @Override
    public Vector<Object> getEquipmentInfo(Object equipmentID) {
        Vector<Object> equipmentInfo = new Vector<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select name, date, company, num, price, totalPrice from vip.equipment where id = ");
        sql.append(equipmentID.toString());
        try{
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while(rs.next()){
                equipmentInfo.add(rs.getString("name"));
                equipmentInfo.add(rs.getString("date"));
                equipmentInfo.add(rs.getString("company"));
                equipmentInfo.add(rs.getInt("num"));
                equipmentInfo.add(rs.getInt("price"));
                equipmentInfo.add(rs.getInt("totalPrice"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return equipmentInfo;
    }

    @Override
    public boolean addEquipment(EquipmentDO equipmentDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert ignore into vip.equipment(id, name, date, company, num, price, totalPrice) values (?, ?, ?, ?, ?, ?, ?)");

        try{
            conn =DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, equipmentDO.getId());
            ps.setString(2, equipmentDO.getName());
            ps.setString(3, equipmentDO.getDate());
            ps.setString(4, equipmentDO.getCompany());
            ps.setInt(5, equipmentDO.getNum());
            ps.setInt(6, equipmentDO.getPrice());
            ps.setInt(7, equipmentDO.getTotalPrice());

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
        sql.append("select id from vip.equipment");
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
    public boolean updateEquipment(EquipmentDO equipmentDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("update vip.equipment set name = ?, date = ?, company = ?, num = ?, price = ?, totalPrice = ? where id = ?");
        try{
            conn =DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, equipmentDO.getName());
            ps.setString(2, equipmentDO.getDate());
            ps.setString(3, equipmentDO.getCompany());
            ps.setInt(4, equipmentDO.getNum());
            ps.setInt(5, equipmentDO.getPrice());
            ps.setInt(6, equipmentDO.getTotalPrice());
            ps.setInt(7, equipmentDO.getId());
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
    public boolean deleteEquipmentInfo(int[] selectedEquipmentIds) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete ignore from vip.equipment where id in (");
        for (int i = 0; i < selectedEquipmentIds.length; i ++){
            if (i == selectedEquipmentIds.length - 1){
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
            for (int i = 0; i < selectedEquipmentIds.length; i ++){
                ps.setInt(i + 1, selectedEquipmentIds[i]);
            }
            return ps.executeUpdate() == selectedEquipmentIds.length;
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
            sql.append("or name like '%").append(request.getSearchKey().trim()).append("%' ");
            sql.append("or company like '%").append(request.getSearchKey().trim()).append("%' ");
            sql.append("or date like '%").append(request.getSearchKey().trim()).append("%' ");

        }
    }
    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();
        while(rs.next()){
            // 处理查出的每一条记录
            Vector<Object> oneRecord = new Vector<>();
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String date = rs.getString("date");
            String company = rs.getString("company");
            int num = rs.getInt("num");
            int price = rs.getInt("price");
            int totalPrice = rs.getInt("totalPrice");
            oneRecord.addElement(id);
            oneRecord.addElement(name);
            oneRecord.addElement(date);
            oneRecord.addElement(company);
            oneRecord.addElement(num);
            oneRecord.addElement(price);
            oneRecord.addElement(totalPrice);
            data.addElement(oneRecord);
        }
        return data;
    }
}
