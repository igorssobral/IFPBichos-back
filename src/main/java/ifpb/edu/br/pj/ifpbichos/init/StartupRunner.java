package ifpb.edu.br.pj.ifpbichos.init;

import ifpb.edu.br.pj.ifpbichos.business.service.CampaignService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner {

    @Autowired
    private CampaignService campaignService;

    @PostConstruct
    public void init() {
        campaignService.updateAllCampaignStatuses();
    }
}
