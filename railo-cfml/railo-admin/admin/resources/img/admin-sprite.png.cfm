<cfset data ='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'>

	<cfsetting showdebugoutput='#false#'>
	<cfif getBaseTemplatePath() == getCurrentTemplatePath()>

		<cfapplication name='__RAILO_STATIC_CONTENT' sessionmanagement='#false#' clientmanagement='#false#' applicationtimeout='#createtimespan( 1, 0, 0, 0 )#'>
				
		<cfset etag 	= 'C3E4769CFBDFC7E25A60F1C25734A673'>
		<cfset mimetype = 'image/png'>		

		<cfset expireDays = 100>
		<cfheader name='Expires' value='#getHTTPTimeString(now() + expireDays)#'>
		<cfheader name='Cache-Control' value='max-age=#86400 * expireDays#'>		
		<cfheader name='ETag' value='''#etag#'''>

		<cfif len( CGI.HTTP_IF_NONE_MATCH ) && ( CGI.HTTP_IF_NONE_MATCH CT etag )>
			<!--- etag matches, return 304 !--->
			<cfheader statuscode='304' statustext='Not Modified'>
			<cfcontent reset='#true#' type='#mimetype#'><cfabort>
		</cfif>

		<!--- file was not cached; send the data !--->
		<cfcontent reset='#true#' type='#mimetype#' variable='#toBinary( data )#'><cfabort>
	<cfelse>

		<cfcontent reset='#true#'><cfoutput>data:image/image/png;base64,#data#</cfoutput><cfabort>
	</cfif>