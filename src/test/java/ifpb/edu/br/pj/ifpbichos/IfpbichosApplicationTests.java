package ifpb.edu.br.pj.ifpbichos;

import org.junit.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@SelectClasses({ifpb.edu.br.pj.ifpbichos.presentation.controller.AuthenticationControllerTest.class})
@SelectPackages({"br.edu.ifpb.dac.sape.system"})
@Suite
@SuiteDisplayName("Tests for functionalities implemented in Sprint 1")
class IfpbichosApplicationTests {



}
