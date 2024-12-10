package ifpb.edu.br.pj.ifpbichos;

import ifpb.edu.br.pj.ifpbichos.presentation.controller.AuthenticationControllerTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@SelectClasses({ ifpb.edu.br.pj.ifpbichos.presentation.controller.CampaignControllerTest.class,
        })
@SelectPackages({"ifpb.edu.br.pj.ifpbichos.presentation.controller",})
@Suite
@SuiteDisplayName("Tests for functionalities implemented")
class IfpbichosApplicationTests {



}
