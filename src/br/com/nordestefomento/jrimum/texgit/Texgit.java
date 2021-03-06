package br.com.nordestefomento.jrimum.texgit;

import static br.com.nordestefomento.jrimum.utilix.ObjectUtil.isNotNull;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.io.File;

import br.com.nordestefomento.jrimum.texgit.engine.TGManager;



public final class Texgit {

	public static final FlatFile<Record> createFlatFile(File xmlDef)throws TexgitException{
		
		FlatFile<Record> iFlatFile = null;
		
		if (isNotNull(xmlDef))
			iFlatFile = TGManager.buildFlatFile(xmlDef);
		
		return iFlatFile;
	}
	
	public static final FlatFile<Record> createFlatFile(String xmlDef)throws TexgitException{
		
		FlatFile<Record> iFlatFile = null;
		
		try{
		
		if(isNotBlank(xmlDef))	
			iFlatFile = createFlatFile(new File(xmlDef));
	
		}catch (Exception e) {
			throw new TexgitException(e);
		}
		
		return iFlatFile;
	}
}
