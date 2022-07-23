package com.lhj.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.constants.MessageConstant;
import com.lhj.dao.MemberDao;
import com.lhj.dao.OrderDao;
import com.lhj.dao.OrderSettingDao;
import com.lhj.entity.Result;
import com.lhj.pojo.Member;
import com.lhj.pojo.Order;
import com.lhj.pojo.OrderSetting;
import com.lhj.service.OrderService;
import com.lhj.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * @author lhj
 * @creat 2022-04-16-15:09
 */
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private OrderSettingDao orderSettingDao;
    @Autowired
    private OrderDao orderDao;

    @Override
    public Result order(Map orderInfo) throws Exception {
        /**
         * 1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
         *
         * 2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
         *
         * 3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约
         *
         * 4、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
         *
         * 5、预约成功，更新当日的已预约人数
         */
        String orderDate = (String) orderInfo.get("orderDate");
        Date date = DateUtils.parseString2Date(orderDate);
        OrderSetting orderSetting = orderSettingDao.getOrderSettingByDate(date);

        if (orderSetting == null || orderSetting.getNumber() <= 0) {
            //当前日期无法预约
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }

        int reservations = orderSetting.getReservations();
        int number = orderSetting.getNumber();
        if (reservations >= number) {
            //预约已满
            return new Result(false, MessageConstant.ORDER_FULL);
        }

        String telephone = (String) orderInfo.get("telephone");
        Member member = memberDao.getMemberByTelephone(telephone);
        Order order = new Order();
        order.setOrderDate(DateUtils.parseString2Date((String) orderInfo.get("orderDate")));
        order.setOrderType((String) orderInfo.get("orderType"));
        order.setOrderStatus(Order.ORDERSTATUS_NO);
        order.setSetmealId(Integer.parseInt((String) orderInfo.get("setmealId")));
        //不是会员的情况
        if (member == null) {
            //-创建新会员
            Member memberNew = new Member();
            memberNew.setName((String) orderInfo.get("name"));
            memberNew.setSex((String) orderInfo.get("sex"));
            memberNew.setIdCard((String) orderInfo.get("idCard"));
            memberNew.setPhoneNumber((String) orderInfo.get("telephone"));
            //--要获取主键
            memberDao.add(memberNew);
            //-设置订单
            order.setMemberId(memberNew.getId());
            //添加订单，并获取主键
            orderDao.order(order);
            //更新已预约人数
            orderSetting.setReservations(reservations + 1);
            orderSettingDao.updateReservationsByDate(orderSetting);
            //预约成功
            return new Result(true,MessageConstant.ORDER_SUCCESS,order);
        }else {
            //-是会员的情况
            Order orderByMemberId = orderDao.findOrderByMemberId(member.getId());
            if (orderByMemberId == null) {
                order.setMemberId(member.getId());
                //添加订单
                orderDao.order(order);
                orderSetting.setReservations(reservations + 1);
                orderSettingDao.updateReservationsByDate(orderSetting);
                return new Result(true,MessageConstant.ORDER_SUCCESS,order);
            }else {
                //不能重复预约
                return new Result(false,MessageConstant.HAS_ORDERED);
            }
        }



    }

    @Override
    public Order findById(Integer id) {
        return orderDao.findById(id);
    }

    @Override
    public Map<String, Object> findOrderSuccessById(Integer id) {
        return orderDao.findOrderSuccessById(id);
    }
}
