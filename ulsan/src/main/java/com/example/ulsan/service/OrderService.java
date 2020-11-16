package com.example.ulsan.service;

import com.example.ulsan.model.CrudInterface;
import com.example.ulsan.model.entity.Orders;
import com.example.ulsan.model.network.Header;
import com.example.ulsan.model.network.Pagination;
import com.example.ulsan.model.network.body.OrderBody;
import com.example.ulsan.repository.CompanyRepository;
import com.example.ulsan.repository.OrderRepository;
import com.example.ulsan.repository.WharfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements CrudInterface<OrderBody> {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    WharfRepository wharfRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Override
    public Header<OrderBody> create(OrderBody request) {
        Orders orders =bodyToOrder(request);
        Orders returned=orderRepository.save(orders);
        return response(returned);
    }

    @Override
    public Header<OrderBody> read(Long id) {
        Optional<Orders> order = orderRepository.findById(id);
        return order.map(temp-> response(temp))
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderBody> update(OrderBody request) {

        Orders orders =orderRepository.getOne(request.getId());
        orders.setType(request.getType())
                .setApprovedStatus(request.getApprovedStatus())
                .setWharfStartDate(request.getWharfStartDate())
                .setWharfEndDate(request.getWharfEndDate())
                .setApplicantName(request.getApplicantName())
                .setApplicantPhoneNumber(request.getApplicantPhoneNumber())
                .setApplicantAddress(request.getApplicantAddress())
                .setShipNumber(request.getShipNumber())
                .setShipYear(request.getShipYear())
                .setShipInCount(request.getShipInCount())
                .setShipApplyCount(request.getShipApplyCount())
                .setShipName(request.getShipName())
                .setShipWeight(request.getShipWeight())
                .setShipLength(request.getShipLength())
                .setShipWidth(request.getShipWidth())
                .setShipExpected(request.getShipExpected())
                .setUsageType(request.getUsageType())
                .setUsageWork(request.getUsageWork())
                .setFacilityCode(request.getFacilityCode())
                .setApprovedNumber(request.getApprovedNumber())
                .setArea(request.getArea())
                .setLocation(request.getLocation())
                .setPurpose(request.getPurpose())
                .setDescription(request.getDescription())
                .setCondition(request.getCondition())
                .setDownCargoOneKind(request.getDownCargoOneKind())
                .setDownCargoOneWeight(request.getDownCargoOneWeight())
                .setDownCargoTwoKind(request.getDownCargoTwoKind())
                .setDownCargoTwoWeight(request.getDownCargoTwoWeight())
                .setDownCargoThreeKind(request.getDownCargoThreeKind())
                .setDownCargoThreeWeight(request.getDownCargoThreeWeight())
                .setUpCargoOneKind(request.getUpCargoOneKind())
                .setUpCargoOneWeight(request.getUpCargoOneWeight())
                .setUpCargoTwoKind(request.getUpCargoTwoKind())
                .setUpCargoTwoWeight(request.getUpCargoTwoWeight())
                .setUpCargoThreeKind(request.getUpCargoThreeKind())
                .setUpCargoThreeWeight(request.getUpCargoThreeWeight())
                .setDeviceCargoOneKind(request.getDeviceCargoOneKind())
                .setDeviceCargoOneWeight(request.getDeviceCargoOneWeight())
                .setDeviceCargoOneNumber(request.getDeviceCargoOneNumber())
                .setDeviceCargoTwoKind(request.getDeviceCargoTwoKind())
                .setDeviceCargoTwoWeight(request.getDeviceCargoTwoWeight())
                .setDeviceCargoTwoNumber(request.getDeviceCargoTwoNumber())
                .setFeeExemptionReason(request.getFeeExemptionReason())
                .setFeeExemptionStartDate(request.getFeeExemptionStartDate())
                .setFeeExemptionEndDate(request.getFeeExemptionEndDate())
                .setFeeUsageStartDate(request.getFeeUsageStartDate())
                .setFeeUsageEndDate(request.getFeeUsageEndDate())
                .setCalculatedDescription(request.getCalculatedDescription())
                .setPartExemption(request.getPartExemption())
                .setPartExemptionReason(request.getPartExemptionReason())
                .setTotalFee(request.getTotalFee())
                .setCollectionRequestDate(request.getCollectionRequestDate())
                .setDateOfPayment(request.getDateOfPayment())
                .setNote(request.getNote())
                .setAppliedStatus(request.getAppliedStatus())
                .setDivision(request.getDivision())
                .setRegisteredAt(request.getRegisteredAt())
                .setCompany(companyRepository.getOne(request.getCompanyId()))
                .setWharf(wharfRepository.getOne(request.getWharfId()));
        Orders returned = orderRepository.save(orders);
        return response(returned);
    }

    @Override
    public Header delete(Long id) {
        Optional<Orders> order = orderRepository.findById(id);
        return order.map(temp->{orderRepository.delete(temp);
            return Header.OK();}).orElseGet(()-> Header.ERROR("데이터 없음"));
    }



    public Header<OrderBody> response(Orders orders){
        OrderBody orderBody=orderToBody(orders);
        return Header.OK(orderBody);
    }

    public Header<List<OrderBody>> readAll(Pageable pageable) {
        Page<Orders> ordersList =orderRepository.findAll(pageable);
        List<OrderBody> orderBodyList = new ArrayList<>();
        for(Orders orders : ordersList)
        {
            OrderBody orderBody = orderToBody(orders);
            orderBodyList.add(orderBody);
        }
        Pagination pagination= Pagination.builder()
                .totalPages(ordersList.getTotalPages())
                .totalElements(ordersList.getTotalElements())
                .currentPage(ordersList.getNumber())
                .currentElements(ordersList.getNumberOfElements())
                .build();
        return Header.OK(orderBodyList, pagination);
    }

    private OrderBody orderToBody(Orders orders) {
        OrderBody orderBody= OrderBody.builder()
                .id(orders.getId())
                .type(orders.getType())
                .approvedStatus(orders.getApprovedStatus())
                .wharfStartDate(orders.getWharfStartDate())
                .wharfEndDate(orders.getWharfEndDate())
                .applicantName(orders.getApplicantName())
                .applicantPhoneNumber(orders.getApplicantPhoneNumber())
                .applicantAddress(orders.getApplicantAddress())
                .shipNumber(orders.getShipNumber())
                .shipYear(orders.getShipYear())
                .shipInCount(orders.getShipInCount())
                .shipApplyCount(orders.getShipApplyCount())
                .shipName(orders.getShipName())
                .shipWeight(orders.getShipWeight())
                .shipLength(orders.getShipLength())
                .shipWidth(orders.getShipWidth())
                .shipExpected(orders.getShipExpected())
                .usageType(orders.getUsageType())
                .usageWork(orders.getUsageWork())
                .facilityCode(orders.getFacilityCode())
                .approvedNumber(orders.getApprovedNumber())
                .area(orders.getArea())
                .location(orders.getLocation())
                .purpose(orders.getPurpose())
                .description(orders.getDescription())
                .condition(orders.getCondition())
                .downCargoOneKind(orders.getDownCargoOneKind())
                .downCargoOneWeight(orders.getDownCargoOneWeight())
                .downCargoTwoKind(orders.getDownCargoTwoKind())
                .downCargoTwoWeight(orders.getDownCargoTwoWeight())
                .downCargoThreeKind(orders.getDownCargoThreeKind())
                .downCargoThreeWeight(orders.getDownCargoThreeWeight())
                .upCargoOneKind(orders.getUpCargoOneKind())
                .upCargoOneWeight(orders.getUpCargoOneWeight())
                .upCargoTwoKind(orders.getUpCargoTwoKind())
                .upCargoTwoWeight(orders.getUpCargoTwoWeight())
                .upCargoThreeKind(orders.getUpCargoThreeKind())
                .upCargoThreeWeight(orders.getUpCargoThreeWeight())
                .deviceCargoOneKind(orders.getDeviceCargoOneKind())
                .deviceCargoOneWeight(orders.getDeviceCargoOneWeight())
                .deviceCargoOneNumber(orders.getDeviceCargoOneNumber())
                .deviceCargoTwoKind(orders.getDeviceCargoTwoKind())
                .deviceCargoTwoWeight(orders.getDeviceCargoTwoWeight())
                .deviceCargoTwoNumber(orders.getDeviceCargoTwoNumber())
                .feeExemptionReason(orders.getFeeExemptionReason())
                .feeExemptionStartDate(orders.getFeeExemptionStartDate())
                .feeExemptionEndDate(orders.getFeeExemptionEndDate())
                .feeUsageStartDate(orders.getFeeUsageStartDate())
                .feeUsageEndDate(orders.getFeeUsageEndDate())
                .calculatedDescription(orders.getCalculatedDescription())
                .partExemption(orders.getPartExemption())
                .partExemptionReason(orders.getPartExemptionReason())
                .totalFee(orders.getTotalFee())
                .collectionRequestDate(orders.getCollectionRequestDate())
                .dateOfPayment(orders.getDateOfPayment())
                .note(orders.getNote())
                .appliedStatus(orders.getAppliedStatus())
                .division(orders.getDivision())
                .registeredAt(orders.getRegisteredAt())
                .companyId(orders.getCompany().getId())
                .wharfId(orders.getWharf().getId())
                .company(orders.getCompany().getName())
                .wharf(orders.getWharf().getName())
                .wharfKind(orders.getWharf().getKind())
                .build();
        return orderBody;
    }
    public Orders bodyToOrder(OrderBody request)
    {
        Orders orders = Orders.builder()
                .type(request.getType())
                .approvedStatus(request.getApprovedStatus())
                .wharfStartDate(request.getWharfStartDate())
                .wharfEndDate(request.getWharfEndDate())
                .applicantName(request.getApplicantName())
                .applicantPhoneNumber(request.getApplicantPhoneNumber())
                .applicantAddress(request.getApplicantAddress())
                .shipNumber(request.getShipNumber())
                .shipYear(request.getShipYear())
                .shipInCount(request.getShipInCount())
                .shipApplyCount(request.getShipApplyCount())
                .shipName(request.getShipName())
                .shipWeight(request.getShipWeight())
                .shipLength(request.getShipLength())
                .shipWidth(request.getShipWidth())
                .shipExpected(request.getShipExpected())
                .usageType(request.getUsageType())
                .usageWork(request.getUsageWork())
                .facilityCode(request.getFacilityCode())
                .approvedNumber(request.getApprovedNumber())
                .area(request.getArea())
                .location(request.getLocation())
                .purpose(request.getPurpose())
                .description(request.getDescription())
                .condition(request.getCondition())
                .downCargoOneKind(request.getDownCargoOneKind())
                .downCargoOneWeight(request.getDownCargoOneWeight())
                .downCargoTwoKind(request.getDownCargoTwoKind())
                .downCargoTwoWeight(request.getDownCargoTwoWeight())
                .downCargoThreeKind(request.getDownCargoThreeKind())
                .downCargoThreeWeight(request.getDownCargoThreeWeight())
                .upCargoOneKind(request.getUpCargoOneKind())
                .upCargoOneWeight(request.getUpCargoOneWeight())
                .upCargoTwoKind(request.getUpCargoTwoKind())
                .upCargoTwoWeight(request.getUpCargoTwoWeight())
                .upCargoThreeKind(request.getUpCargoThreeKind())
                .upCargoThreeWeight(request.getUpCargoThreeWeight())
                .deviceCargoOneKind(request.getDeviceCargoOneKind())
                .deviceCargoOneWeight(request.getDeviceCargoOneWeight())
                .deviceCargoOneNumber(request.getDeviceCargoOneNumber())
                .deviceCargoTwoKind(request.getDeviceCargoTwoKind())
                .deviceCargoTwoWeight(request.getDeviceCargoTwoWeight())
                .deviceCargoTwoNumber(request.getDeviceCargoTwoNumber())
                .feeExemptionReason(request.getFeeExemptionReason())
                .feeExemptionStartDate(request.getFeeExemptionStartDate())
                .feeExemptionEndDate(request.getFeeExemptionEndDate())
                .feeUsageStartDate(request.getFeeUsageStartDate())
                .feeUsageEndDate(request.getFeeUsageEndDate())
                .calculatedDescription(request.getCalculatedDescription())
                .partExemption(request.getPartExemption())
                .partExemptionReason(request.getPartExemptionReason())
                .totalFee(request.getTotalFee())
                .collectionRequestDate(request.getCollectionRequestDate())
                .dateOfPayment(request.getDateOfPayment())
                .note(request.getNote())
                .appliedStatus(request.getAppliedStatus())
                .division(request.getDivision())
                .registeredAt(request.getRegisteredAt())
                .company(companyRepository.getOne(request.getCompanyId()))//TODO: getOne method시 에러 잡기
                .wharf(wharfRepository.getOne(request.getWharfId()))
                .build();
        return orders;
    }
}