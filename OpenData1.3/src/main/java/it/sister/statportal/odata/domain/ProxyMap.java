package it.sister.statportal.odata.domain;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(versionField = "", table = "proxy_map", schema = "public")
@RooDbManaged(automaticallyDelete = true)
public class ProxyMap {
}
