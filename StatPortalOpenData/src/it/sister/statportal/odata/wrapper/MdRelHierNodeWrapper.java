package it.sister.statportal.odata.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.odata4j.core.OEntities;
import org.odata4j.core.OEntity;
import org.odata4j.core.OEntityKey;
import org.odata4j.core.OLink;
import org.odata4j.core.OLinks;
import org.odata4j.core.OProperties;
import org.odata4j.core.OProperty;
import org.odata4j.edm.EdmEntitySet;
import org.odata4j.edm.EdmEntityType;
import org.odata4j.edm.EdmProperty;
import org.odata4j.edm.EdmSimpleType;
import org.odata4j.producer.EntityResponse;
import org.odata4j.producer.ODataProducer;

/**
 * Wrapper per le entit� di tipo MdRealHierNode
 *
 */
public class MdRelHierNodeWrapper {
	
	private static String typeName = "MdRelHierNode";
    
    /**
	 * Fornisce la definizione del modello delle entit� per quanto riguarda il wrapper delle tabelle MD_REL_HIER_NODE
	 * @param namespace il namespace da applicare al modello. � uguale per tutte le entit� e quindi viene passato come parametro.
	 * @return L'entity set con la struttura del wrapper MdRelHierNode
	 */
	public static EdmEntityType.Builder getEntityModel(final String namespace) {
		final List<EdmProperty.Builder> properties = new ArrayList<EdmProperty.Builder>();
		properties.add(EdmProperty.newBuilder("FkField").setType(EdmSimpleType.STRING));

		return EdmEntityType.newBuilder().setNamespace(namespace).setName(typeName).addKeys("FkField").addProperties(properties);
	}
	
	/**
	 * Crea un'entit� di tipo MdRelHierNode
	 * @param ees l'entity set
	 * @param entity l'entit� da cui estrarre le informazioni
	 * @param baseProducer il producer che ha creato l'entit� passata come parametro
	 * @return un'entit� di tipo MdRelHierNode
	 */
	public static OEntity createInstance(final EdmEntitySet ees, final OEntity entity, final ODataProducer baseProducer) {
		final List<OProperty<?>> properties = new ArrayList<OProperty<?>>();
        final List<OLink> links = new ArrayList<OLink>();			
    	properties.add(OProperties.string("FkField", (String)entity.getProperty("fkField").getValue()));
    	final EntityResponse idParentResponse = ((EntityResponse)baseProducer.getNavProperty(typeName, entity.getEntityKey(), "idParentNode", null));
    	final Integer idParent = (Integer)idParentResponse.getEntity().getProperty("uid").getValue();
    	links.add(OLinks.relatedEntity("parentNode", "IdParent", "MdHierNode("+idParent+")"));
    	final EntityResponse idChildResponse = ((EntityResponse)baseProducer.getNavProperty(typeName, entity.getEntityKey(), "idChildNode", null));
    	final Integer idChild = (Integer)idChildResponse.getEntity().getProperty("uid").getValue();
    	links.add(OLinks.relatedEntity("childNode", "IdChild", "MdHierNode("+idChild+")"));
		return OEntities.create(ees, OEntityKey.create("idParent",idParent,"idChild", idChild), properties, links);
	}
}
