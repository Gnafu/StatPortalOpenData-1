package it.sister.statportal.odata.wrapper;

//import it.sister.statportal.odata.domain.MdHierarchy;

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
import org.odata4j.producer.ODataProducer;
import org.odata4j.producer.PropertyResponse;

/**
 * Wrapper per le entit� di tipo MdHierarchy
 *
 */
public class MdHierarchyWrapper {

	private static String typeName = "MdHierarchy";
	
	private static final String ID_LITERAL = "id";
	
	/**
	 * Fornisce la definizione del modello delle entit� per quanto riguarda il wrapper delle tabelle MD_HIERARCHY
	 * @param namespace il namespace da applicare al modello. � uguale per tutte le entit� e quindi viene passato come parametro.
	 * @return L'entity set con la struttura del wrapper MdHierarchy
	 */
	public static EdmEntityType.Builder getEntityModel(final String namespace) {
		final List<EdmProperty.Builder> properties = new ArrayList<EdmProperty.Builder>();
		properties.add(EdmProperty.newBuilder(ID_LITERAL).setType(EdmSimpleType.INT32));
		properties.add(EdmProperty.newBuilder("name").setType(EdmSimpleType.STRING));
		properties.add(EdmProperty.newBuilder("description").setType(EdmSimpleType.STRING));
		return EdmEntityType.newBuilder().setNamespace(namespace).setName(typeName).addKeys(ID_LITERAL).addProperties(properties);
	}
	
	/**
	 * Crea un'entit� di tipo MdHierarchy
	 * @param ees l'entity set
	 * @param entity l'entit� da cui estrarre i valori
	 * @param baseProducer il producer che ha generato l'entit� passata come parametro
	 * @return un'entit� di tipo MdHierarchy
	 */
	public static OEntity createInstance(final EdmEntitySet ees, final OEntity entity, final ODataProducer baseProducer) {
		final List<OProperty<?>> properties = new ArrayList<OProperty<?>>();
        final List<OLink> links = new ArrayList<OLink>();			
        final Integer id = (Integer)entity.getProperty(ID_LITERAL).getValue();
        final OEntityKey key = OEntityKey.create(id);
        properties.add(OProperties.int32(ID_LITERAL, id));
		properties.add(OProperties.string("name", (String)entity.getProperty("name").getValue()));
		properties.add(OProperties.string("description", (String)entity.getProperty("description").getValue()));
		final PropertyResponse idHierType = ((PropertyResponse)baseProducer.getNavProperty(typeName, key, "idLuHierType", null));
		links.add(OLinks.relatedEntity("hierarchyType", "IdLuHierType", "MdLuHierType("+(Integer)idHierType.getProperty().getValue()+")"));
		links.add(OLinks.relatedEntities("nodesByHierarchy", "NodesByHierarchy", "NodesByHierarchy("+id+")"));
		return OEntities.create(ees, key, properties, links);
	}
}
