package it.sister.statportal.odata.etl.exchange;

/**
 * Informazioni su un file che � stato caricato sul server.
 * Le informazioni riguardano le caratteristiche del file 
 * che servono per il caricamento di un dato in StatPortalOpenData:
 * - se il file � strutturato o meno
 * - nel caso di file strutturato, quali sono le colonne del file.
 *
 */
public class FileUploadInfo {

	//Codice che descrive lo stato del file
	protected int statusCode;
	
	//Descrizione in linguaggio naturale dello stato del file
	protected String statusDescription;
	
	//Informazioni sulle colonne presenti nel file.
	//Nel caso di file non strutturato questo array sar� vuoto.
	protected ColumnInfo[] columnInfo;
	
	//Tipo di file caricato
	protected String fileType;
	
	/**
	 * Costruisce l'oggetto
	 * @param statusCode il codice che descrive lo stato del file
	 * @param statusDescription la descrizione in linguaggio naturale dello stato del file
	 * @param columnInfo informazioni sulle colonne presenti nel file (vuoto se il file non � strutturato)
	 * @param fileType tipo di file caricato
	 */
	public FileUploadInfo(int statusCode, String statusDescription, ColumnInfo[] columnInfo, String fileType){
		this.statusCode = statusCode;
		this.statusDescription = statusDescription;
		this.columnInfo = columnInfo;
		this.fileType = fileType;
	}
	
	/**
	 * Costruisce l'oggetto (da usare quando il file non � strutturato o c'� un errore)
	 * @param statusCode il codice che descrive lo stato del file
	 * @param statusDescription la descrizione in linguaggio naturale dello stato del file
	 * @param fileType tipo di file caricato
	 */
	public FileUploadInfo(int statusCode, String statusDescription, String fileType){
		this.statusCode = statusCode;
		this.statusDescription = statusDescription;
		this.columnInfo = new ColumnInfo[0];
		this.fileType = fileType;
	}	
	
	public FileUploadInfo(){}
}
