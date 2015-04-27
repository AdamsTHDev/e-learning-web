package com.adms.elearning.web.bean.courseinfo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;

import com.adms.elearning.web.bean.base.BaseBean;
import com.adms.elearning.web.util.MessageUtils;

@ManagedBean
@ViewScoped
public class CourseInfoView extends BaseBean {

	private static final long serialVersionUID = 5165374821258297160L;
	
	private List<SelectItem> selectionCampaigns;
	private List<SelectItem> selectionLevels;
	
	private String selectedCampaign;
	private String selectedLevel;
	
	public CourseInfoView() {
		selectionCampaigns = new ArrayList<>();
		selectionLevels = new ArrayList<>();
	}
	
	@PostConstruct
	private void init()	{

		selectionCampaigns.add(new SelectItem("", "Campaign"));
		selectionCampaigns.add(new SelectItem("campaign_1", "campaign 1"));
		selectionCampaigns.add(new SelectItem("campaign_2", "campaign 2"));
		selectionCampaigns.add(new SelectItem("campaign_3", "campaign 3"));
		selectionCampaigns.add(new SelectItem("campaign_4", "campaign 4"));
		selectionCampaigns.add(new SelectItem("campaign_5", "campaign 5"));

		selectionLevels.add(new SelectItem("", "Level"));
		selectionLevels.add(new SelectItem("TSR", "TSR"));
		selectionLevels.add(new SelectItem("SUP", "SUP"));
		selectionLevels.add(new SelectItem("DSM", "DSM"));
	}
	
	public String proceed() {
		try {
			boolean flag = false;
			
			if(StringUtils.isNotBlank(selectedCampaign) && StringUtils.isNotBlank(selectedLevel)) {
				flag = true;
			} else {
				MessageUtils.getInstance().addErrorMessage("msg", "Please, select course");
			}
			
			if(flag) {
				  
				return "question/questions?faces-redirect=true";
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getSelectedCampaign() {
		return selectedCampaign;
	}
	public void setSelectedCampaign(String selectedCampaign) {
		this.selectedCampaign = selectedCampaign;
	}
	public String getSelectedLevel() {
		return selectedLevel;
	}
	public void setSelectedLevel(String selectedLevel) {
		this.selectedLevel = selectedLevel;
	}

	public List<SelectItem> getCampaignSelection() {
		return selectionCampaigns;
	}

	public void setCampaignSelection(List<SelectItem> selectionCampaigns) {
		this.selectionCampaigns = selectionCampaigns;
	}

	public List<SelectItem> getLevelSelection() {
		return selectionLevels;
	}

	public void setLevelSelection(List<SelectItem> selectionLevels) {
		this.selectionLevels = selectionLevels;
	}
}
