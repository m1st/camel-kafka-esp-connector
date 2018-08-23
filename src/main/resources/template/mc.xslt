<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns0="urn:AF_MK_Entities">
    <xsl:template match="/">
        <ns0:AuthorizationResponse xmlns:ns0="urn:AF_MK_Entities">
            <xsl:apply-templates select="/*/*"/>
        </ns0:AuthorizationResponse>
    </xsl:template>
    <xsl:template match="/*/*">
        <xsl:element name="ns0:{local-name()}">
            <xsl:apply-templates select="node()|@*" />
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>