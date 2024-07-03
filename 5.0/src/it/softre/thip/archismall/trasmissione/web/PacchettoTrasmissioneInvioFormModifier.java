package it.softre.thip.archismall.trasmissione.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.web.WebFormModifier;

import it.softre.thip.archismall.trasmissione.PacchettoTrasmissione;
import it.softre.thip.archismall.trasmissione.PacchettoTrasmissioneInvio;
import it.thera.thip.cs.ColonneFiltri;

public class PacchettoTrasmissioneInvioFormModifier extends WebFormModifier {

	@Override
	public void writeHeadElements(JspWriter out) throws IOException {

	}

	@Override
	public void writeBodyStartElements(JspWriter out) throws IOException {

	}

	@SuppressWarnings({ "rawtypes", "unlikely-arg-type" })
	@Override
	public void writeFormStartElements(JspWriter out) throws IOException {
		List lstChiaviSelected = (List) getServletEnvironment().getRequest().getAttribute("lstChiaviSelected");
		if(lstChiaviSelected != null && !lstChiaviSelected.equals("")) {
			out.println("<input type=\"hidden\" id=\"thMDVExtractor\" name=\"thMDVExtractor\" >");
			out.println("<script language=\"javascript1.2\">");
			out.println("document.getElementById('thMDVExtractor').value='it.thera.thip.cs.web.StampaSingolaScreenDataExtractor';");
			out.println("</script>");
			getBODataCollector().setOnBORecursive();
			java.util.Iterator lstChiaviSelectedIte = ( java.util.Iterator)lstChiaviSelected.iterator();
			String keysSel = "";
			PacchettoTrasmissioneInvio myBatch = (PacchettoTrasmissioneInvio)getBODataCollector().getBo();
			while (lstChiaviSelectedIte.hasNext()) {
				String nextKey = (String)lstChiaviSelectedIte.next();
				keysSel += nextKey;
				if(lstChiaviSelectedIte.hasNext())
					keysSel += ColonneFiltri.LISTA_SEP;
			}
			myBatch.setChiaviSelezionati(keysSel);
			getBODataCollector().loadAttValue();
		}

	}

	@Override
	public void writeFormEndElements(JspWriter out) throws IOException {
		PacchettoTrasmissione packet;
		try {
			packet = (PacchettoTrasmissione) PacchettoTrasmissione.elementWithKey(
					PacchettoTrasmissione.class, ((PacchettoTrasmissioneInvio)getBODataCollector().getBo()).getChiaviSelezionati().toString(), PersistentObject.NO_LOCK);
			if(packet != null) {
				out.println("<script language=\"javascript1.2\">");
				out.println("document.getElementById('descr').innerHTML = '"+(packet.getIdLancio()+"\\ ---- "+packet.getDescrizione())+"';");
				out.println("</script>");
			}
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
	}

	@Override
	public void writeBodyEndElements(JspWriter out) throws IOException {

	}
}
