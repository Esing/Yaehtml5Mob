package com.yaeyi.mobile.common;

public class CommonConstant {
	// Session对象
	public static final String Y3U_SESSION_USER = "Y3USessionUser";

	public static final Integer SYS_PLATFORM_USERS_SEQ_TYPE = 9000; // UserID
																	// SYS_PlatformUsers

	public static final Integer SYS_CLINICS_SEQ_TYPE = 9001; // ClinicID
																// SYS_Clinics

	public static final Integer SYS_DOCTORS_SEQ_TYPE = 9002; // DoctorID
																// SYS_Doctors

	public static final Integer SYS_PATIENTS_SEQ_TYPE = 9003; // PatientID
																// TMP_Patients,
																// SYS_Patients

	public static final Integer SYS_EMPLOYEES_SEQ_TYPE = 9004; // EmployeeID
																// SYS_Employees

	public static final Integer BUS_BOOKEDORDERS_SEQ_TYPE = 9005; // OrderID
																	// BUS_BookedOrders

	public static final Integer BUS_ADVICECONTENT_SEQ_TYPE = 9006; // AdviceCode
																	// BUS_AdviceContent

	public static final Integer BUS_SYMPTOMCONTENT_SEQ_TYPE = 9007; // SymptomCode
																	// BUS_SymptomContent

	public static final Integer BUS_SERVICELIST_SEQ_TYPE = 9008; // ServiceCode
																	// BUS_ServiceList

	public static final Integer BUS_MATERIALLIST_SEQ_TYPE = 9009; // MaterialCode
																	// BUS_MaterialList

	public static final Integer BOM_MATERIALINVENTORY_SEQ_TYPE = 9010; // InventoryID
																		// BOM_MaterialInventory

	public static final Integer BOM_MATERIALPURCHASE_SEQ_TYPE = 9011; // PurchaseID
																		// BOM_MaterialPurchase

	public static final Integer BOM_MATERIALDELIVERY_SEQ_TYPE = 9012; // DeliveryID
																		// BOM_MaterialDelivery

	public static final Integer BOM_MATERIALREFUND_SEQ_TYPE = 9013; // RefundID
																	// BOM_MaterialRefund

	public static final Integer PAY_TARIFFTEMPLATE_SEQ_TYPE = 9014; // TariffCode
																	// PAY_TariffTemplate

	public static final Integer PAY_TARIFFSOLUTION_SEQ_TYPE = 9015; // ProductID
																	// PAY_TariffSolution

	public static final Integer PAY_TREATEDBILLS_SEQ_TYPE = 9016; // BillNoID
																	// PAY_TreatedBills

	public static final Integer PAY_BILLPAYDETAIL_SEQ_TYPE = 9017; // BillPayID
																	// PAY_BillPayDetail

	public static final Integer PAY_PATIENTCHARGES_SEQ_TYPE = 9018; // PayID
																	// PAY_PatientCharges

	public static final Integer PAY_PATIENTCONSUME_SEQ_TYPE = 9019; // ConsumeID
																	// PAY_PatientConsume

	public static final Integer BUS_TREATEDCOST_SEQ_TYPE = 9020; // ProductID
																	// BUS_TreatedCost
	// 订单状态

	public static final Integer ORDER_STATE_PENDING_CONFIRMED = 10;// 待确认

	public static final Integer ORDER_STATE_CONFIRMED = 20;// 已确认

	public static final Integer ORDER_STATE_TREATED = 30;// 已接诊

	public static final Integer ORDER_STATE_TREATING = 40;// 就诊中

	public static final Integer ORDER_STATE_TREAT_FINISH = 50;// 就诊完成

	public static final Integer SERVICECODE_PRODUCTTYPE = 10;// 治疗项目 区分是治疗项目或者材料

	public static final Integer MATERIALCODE_EPRODUCTTYPE = 20;// 治疗项目
																// 区分是治疗项目或者材料

	public static final Integer BILL_PAYSTATUS_NOT_BEGINNING = 10;// 未开始 订单支付状态

	public static final Integer BILL_PAYSTATUS_WAIT_PAID = 20;// 待支付 订单支付状态

	public static final Integer BILL_PAYSTATUS_ALREADY_PAID = 30;// 已支付 订单支付状态

	public static final Integer EMPLOYEE_INCUMBENCY_STATE = 10;// 在职 员工在职状态

	public static final Integer EMPLOYEE_LEAVE_OFFICE_STATE = 20;// 在职 员工在职状态

	public static final Integer WORK_PATTERN_FULL_TIME_STATE = 10;// 全职
																	// 医生工作模式（全职或兼职）

	public static final Integer WORK_PATTERN_PART_TIME_STATE = 20;// 兼职
																	// 医生工作模式（全职或兼职）

	public static final Integer SYS_DICT_TYPE_CODE_12001 = 12001; // 字典表病症code
	public static final Integer SYS_DICT_TYPE_CODE_12110 = 12110; // 字典表治疗项目code
	public static final Integer SYS_DICT_TYPE_CODE_12120 = 12120; // 字典表材料code

	// 用户默认头像图片
	public static final String LOGIN_USER_DEFAULT_ICON = "/oa/theme/assets/admin/layout2/img/avatar3_small.jpg";
}
