package org.warmsheep.entity;

import java.math.BigDecimal;

import org.warmsheep.framework.db.entity.BaseEntity;

/**
 * 账户详情
 * @author Warmsheep
 *
 */
public class User extends BaseEntity{
	
	private String userName;		//用户名
	private String loginPwd;		//登录密码
	private BigDecimal total;		//资产总额
	private BigDecimal netAsset;	//净资产
	private BigDecimal availableCnyDisplay;	//可用人民币	
	private BigDecimal availableBtcDisplay;	//可用比特币
	private BigDecimal availableLtcDisplay;	//可用莱特币
	private BigDecimal frozenCnyDisplay;	//冻结人民币
	private BigDecimal frozenBtcDisplay;	//冻结比特币
	private BigDecimal frozenLtcDisplay;	//冻结莱特币
	private BigDecimal loanCnyDisplay;		//待还人民币
	private BigDecimal loanBtcDisplay;		//待还比特币
	private BigDecimal loanLtcDisplay;		//待还莱特币
	private String huobiAccessKey;	//火币访问密钥
	private String huobiSecretKey;	//火币安全密钥

	/**
	 * 用户名
	 * @return
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 用户名
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 登录密码
	 * @return
	 */
	public String getLoginPwd() {
		return loginPwd;
	}
	/**
	 * 登录密码
	 * @param loginPwd
	 */
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
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
	public BigDecimal getNetAsset() {
		return netAsset;
	}
	/**
	 * 净资产
	 * @param netAsset
	 */
	public void setNetAsset(BigDecimal netAsset) {
		this.netAsset = netAsset;
	}
	/**
	 * 可用人民币
	 * @return
	 */
	public BigDecimal getAvailableCnyDisplay() {
		return availableCnyDisplay;
	}
	/**
	 * 可用人民币
	 * @param availableCnyDisplay
	 */
	public void setAvailableCnyDisplay(BigDecimal availableCnyDisplay) {
		this.availableCnyDisplay = availableCnyDisplay;
	}
	/**
	 * 可用比特币
	 * @return
	 */
	public BigDecimal getAvailableBtcDisplay() {
		return availableBtcDisplay;
	}
	/**
	 * 可用比特币
	 * @param availableBtcDisplay
	 */
	public void setAvailableBtcDisplay(BigDecimal availableBtcDisplay) {
		this.availableBtcDisplay = availableBtcDisplay;
	}
	/**
	 * 可用莱特币
	 * @return
	 */
	public BigDecimal getAvailableLtcDisplay() {
		return availableLtcDisplay;
	}
	/**
	 * 可用莱特币
	 * @param availableLtcDisplay
	 */
	public void setAvailableLtcDisplay(BigDecimal availableLtcDisplay) {
		this.availableLtcDisplay = availableLtcDisplay;
	}
	/**
	 * 冻结人民币
	 * @return
	 */
	public BigDecimal getFrozenCnyDisplay() {
		return frozenCnyDisplay;
	}
	/**
	 * 冻结人民币
	 * @param frozenCnyDisplay
	 */
	public void setFrozenCnyDisplay(BigDecimal frozenCnyDisplay) {
		this.frozenCnyDisplay = frozenCnyDisplay;
	}
	/**
	 * 冻结比特币
	 * @return
	 */
	public BigDecimal getFrozenBtcDisplay() {
		return frozenBtcDisplay;
	}
	/**
	 * 冻结比特币
	 * @param frozenBtcDisplay
	 */
	public void setFrozenBtcDisplay(BigDecimal frozenBtcDisplay) {
		this.frozenBtcDisplay = frozenBtcDisplay;
	}
	/**
	 * 冻结莱特币
	 * @return
	 */
	public BigDecimal getFrozenLtcDisplay() {
		return frozenLtcDisplay;
	}
	/**
	 * 冻结莱特币
	 * @param frozenLtcDisplay
	 */
	public void setFrozenLtcDisplay(BigDecimal frozenLtcDisplay) {
		this.frozenLtcDisplay = frozenLtcDisplay;
	}
	/**
	 * 待还人民币
	 * @return
	 */
	public BigDecimal getLoanCnyDisplay() {
		return loanCnyDisplay;
	}
	/**
	 * 待还人民币
	 * @param loanCnyDisplay
	 */
	public void setLoanCnyDisplay(BigDecimal loanCnyDisplay) {
		this.loanCnyDisplay = loanCnyDisplay;
	}
	/**
	 * 待还比特币
	 * @return
	 */
	public BigDecimal getLoanBtcDisplay() {
		return loanBtcDisplay;
	}
	/**
	 * 待还比特币
	 * @param loanBtcDisplay
	 */
	public void setLoanBtcDisplay(BigDecimal loanBtcDisplay) {
		this.loanBtcDisplay = loanBtcDisplay;
	}
	/**
	 * 待还莱特币
	 * @return
	 */
	public BigDecimal getLoanLtcDisplay() {
		return loanLtcDisplay;
	}
	/**
	 * 待还莱特币
	 * @param loanLtcDisplay
	 */
	public void setLoanLtcDisplay(BigDecimal loanLtcDisplay) {
		this.loanLtcDisplay = loanLtcDisplay;
	}
	/**
	 * 火币访问密钥
	 * @return
	 */
	public String getHuobiAccessKey() {
		return huobiAccessKey;
	}
	/**
	 * 火币访问密钥
	 * @param huobiAccessKey
	 */
	public void setHuobiAccessKey(String huobiAccessKey) {
		this.huobiAccessKey = huobiAccessKey;
	}
	/**
	 * 火币安全密钥
	 * @return
	 */
	public String getHuobiSecretKey() {
		return huobiSecretKey;
	}
	/**
	 * 火币安全密钥
	 * @param huobiSecretKey
	 */
	public void setHuobiSecretKey(String huobiSecretKey) {
		this.huobiSecretKey = huobiSecretKey;
	}
	
	
}
