package com.atropos.generator.ceylon.code;

import java.util.Arrays;
import java.util.Collection;

import com.atropos.generator.ceylon.CeylonLanguageUtils;
import com.atropos.generator.code.CodeFile;
import com.atropos.generator.code.GameInfoCodeUnit;

public class CeylonGameInfo implements GameInfoCodeUnit {

	private final CeylonCodeFile gameInfoFile;
	private final String credits;
	private final String gameName;
	
	public CeylonGameInfo(final String credits, final String gameName) {
		this.credits = credits;
		this.gameName = gameName;
		
		final String creditsCode = toSafeCode( credits );
		
		this.gameInfoFile = new CeylonCodeFile() {
			
			@Override
			public String getCode() {
				return "shared class " + getClassname() + "() {\n" +
						"\tshared String credits = \"" + creditsCode + "\";\n\n" +									
						"}\n";
			}
			
			@Override
			public String getClassname() {
				return getInfoClassname();
			}
		};
	}
	
	@Override
	public Collection<CodeFile> getFiles() {
		return Arrays.<CodeFile>asList(gameInfoFile);
	}

	@Override
	public String getCredits() {
		return credits;
	}

	private String toSafeCode( String unsafe ) {
		if ( null == unsafe ) {
			return "null";
		}
		//TODO - santise
		return unsafe;
	}

	@Override
	public String getInfoClassname() {
		return CeylonLanguageUtils.toValidClassname(gameName + "Info");
	}
	
}
