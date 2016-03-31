package com.cike.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

public class XmlUtil {
	private static XStream xstream;
	static {
		xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
	}

	// XML to object.
	public static Object xml2Object(String inputXml, Class<?> type) throws Exception {
		if (null == inputXml || "".equals(inputXml)) {
			return null;
		}
		xstream.alias("xml", type);
		return xstream.fromXML(inputXml);
	}

	// object to XML.
	public static String object2Xml(Object ro, Class<?> types) throws Exception {
		if (null == ro) {
			return null;
		}
		xstream.alias("xml", types);
		return xstream.toXML(ro);
	}
}
