package org.warmsheep.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 账户详情
 * @author Warmsheep
 *
 */
public class AccountInfoVo implements Serializable{

	private BigDecimal total;		//资产总额
	
	private BigDecimal net_asset;	//净资产
	
	private BigDecimal available_cny_display;	//可用人民币	
	
	private BigDecimal available_btc_display;	//可用比特币
	
	private BigDecimal available_ltc_display;	//可用莱特币
	
	private BigDecimal frozen_cny_display;		//冻结人民币
	
	private BigDecimal frozen_btc_display;		//冻结比特币
	
	private BigDecimal frozen_ltc_display;		//冻结莱特币
	
	private BigDecimal loan_cny_display;		//待还人民币
	
	private BigDecimal loan_btc_display;		//待还比特币
	
	private BigDecimal loan_ltc_display;		//待还莱特币

	/**
	 * 资产总额
	 * @return
	 */
	public BigDecimal getTotal() {
		return total;
	}
	/**
	 * 资产总额
	 * @param total
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	/**
	 * 净资产
	 * @return
	 */
	public BigDecimal getNet_asset() {
		return net_asset;
	}
	/**
	 * 净资产
	 * @param net_asset
	 */
	public void setNet_asset(BigDecimal net_asset) {
		this.net_asset = net_asset;
	}
	/**
	 * 可用人民币
	 * @return
	 */
	public BigDecimal getAvailable_cny_display() {
		return available_cny_display;
	}
	/**
	 * 可用人民币
	 * @param available_cny_display
	 */
	public void setAvailable_cny_display(BigDecimal available_cny_display) {
		this.available_cny_display = available_cny_display;
	}
	/**
	 * 可用比特币
	 * @return
	 */
	public BigDecimal getAvailable_btc_display() {
		return available_btc_display;
	}
	/**
	 * 可用比特币
	 * @param available_btc_display
	 */
	public void setAvailable_btc_display(BigDecimal available_btc_display) {
		this.available_btc_display = available_btc_display;
	}
	/**
	 * 可用莱特币
	 * @return
	 */
	public BigDecimal getAvailable_ltc_display() {
		return available_ltc_display;
	}
	/**
	 * 可用莱特币
	 * @param available_ltc_display
	 */
	public void setAvailable_ltc_display(BigDecimal available_ltc_display) {
		this.available_ltc_display = available_ltc_display;
	}
	/**
	 * 冻结人民币
	 * @return
	 */
	public BigDecimal getFrozen_cny_display() {
		return frozen_cny_display;
	}
	/**
	 * 冻结人民币
	 * @param frozen_cny_display
	 */
	public void setFrozen_cny_display(BigDecimal frozen_cny_display) {
		this.frozen_cny_display = frozen_cny_display;
	}
	/**
	 * 冻结比特币
	 * @return
	 */
	public BigDecimal getFrozen_btc_display() {
		return frozen_btc_display;
	}
	/**
	 * 冻结比特币
	 * @param frozen_btc_display
	 */
	public void setFrozen_btc_display(BigDecimal frozen_btc_display) {
		this.frozen_btc_display = frozen_btc_display;
	}
	/**
	 * 冻结莱特币
	 * @return
	 */
	public BigDecimal getFrozen_ltc_display() {
		return frozen_ltc_display;
	}
	/**
	 * 冻结莱特币
	 * @param frozen_ltc_display
	 */
	public void setFrozen_ltc_display(BigDecimal frozen_ltc_display) {
		this.frozen_ltc_display = frozen_ltc_display;
	}
	/**
	 * 待还人民币
	 * @return
	 */
	public BigDecimal getLoan_cny_display() {
		return loan_cny_display;
	}
	/**
	 * 待还人民币
	 * @param loan_cny_display
	 */
	public void setLoan_cny_display(BigDecimal loan_cny_display) {
		this.loan_cny_display = loan_cny_display;
	}
	/**
	 * 待还比特币
	 * @return
	 */
	public BigDecimal getLoan_btc_display() {
		return loan_btc_display;
	}
	/**
	 * 待还比特币
	 * @param loan_btc_display
	 */
	public void setLoan_btc_display(BigDecimal loan_btc_display) {
		this.loan_btc_display = loan_btc_display;
	}
	/**
	 * 待还莱特币
	 * @return
	 */
	public BigDecimal getLoan_ltc_display() {
		return loan_ltc_display;
	}
	/**
	 * 待还莱特币
	 * @param loan_ltc_display
	 */
	public void setLoan_ltc_display(BigDecimal loan_ltc_display) {
		this.loan_ltc_display = loan_ltc_display;
	}
	
	
}
