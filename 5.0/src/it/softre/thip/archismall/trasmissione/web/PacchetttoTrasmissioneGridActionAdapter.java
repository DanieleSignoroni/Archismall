package it.softre.thip.archismall.trasmissione.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.WebMenuBar;
import com.thera.thermfw.web.WebToolBar;
import com.thera.thermfw.web.WebToolBarButton;
import com.thera.thermfw.web.servlet.GridActionAdapter;

import it.softre.thip.archismall.trasmissione.PacchetttoTrasmissione;
import it.thera.thip.crm.anagrMarketing.web.SegmentazioneMktgHelper;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 15/05/2024
 * <br><br>
 * <b>71XXX	DSSOF3	15/05/2024</b>
 * <p>Prima stesura.<br>
 *  
 * </p>
 */

public class PacchetttoTrasmissioneGridActionAdapter extends GridActionAdapter {
	
	private static final long serialVersionUID = 1L;

	private static final String REPLICA_PACCHETTI_RES = "it.softre.thip.archismall.trasmissione.resources.PacchetttoTrasmissione";
	private static final String REPLICA_PACCHETTI_IMG = "it/softre/thip/archismall/trasmissione/img/replica.gif";
	private static final String REPLICA_PACCHETTI = "REPLICA_PACCHETTI";
	
	private static final String INVIA_PACCHETTO_RES = "it.softre.thip.archismall.trasmissione.resources.PacchetttoTrasmissione";
	private static final String INVIA_PACCHETTO_IMG = "it/softre/thip/archismall/trasmissione/img/invio.gif";
	private static final String INVIA_PACCHETTO = "INVIA_PACCHETTO";

	@Override
	public void modifyToolBar(WebToolBar toolBar) {
		super.modifyToolBar(toolBar);
		toolBar.removeButton("New");
		toolBar.removeButton("Open");
		toolBar.removeButton("Copy");
		toolBar.removeButton("Delete");
		toolBar.removeButton("View");
		toolBar.removeButton("NavDocDgt");

		//ora aggiungiamo i nostri bottoni personalizzati

		//1° serve per popolare la tabella
		WebToolBarButton replicaPacchetti = 
				new WebToolBarButton
				(REPLICA_PACCHETTI,
						"action_submit",
						"same",
						"no",
						REPLICA_PACCHETTI_RES,
						REPLICA_PACCHETTI,
						REPLICA_PACCHETTI_IMG,
						REPLICA_PACCHETTI,
						"none",
						false);
		toolBar.addButton(replicaPacchetti);

		//2° serve per inviare un singolo pacchetto verso Archismall
		
		WebToolBarButton inviaPacchettoArchismall = 
				new WebToolBarButton
				(REPLICA_PACCHETTI,
						"action_submit",
						"new",
						"no",
						INVIA_PACCHETTO_RES,
						INVIA_PACCHETTO,
						INVIA_PACCHETTO_IMG,
						INVIA_PACCHETTO,
						"single",
						false);
		toolBar.addButton(inviaPacchettoArchismall);
	}

	@Override
	public void modifyMenuBar(WebMenuBar menuBar) {
		super.modifyMenuBar(menuBar);
		menuBar.removeMenu("ListMenu.New");
		menuBar.removeMenu("ListMenu.NewTemplate");
		menuBar.removeMenu("SelectedMenu.Open");
		menuBar.removeMenu("SelectedMenu.Copy");
		menuBar.removeMenu("SelectedMenu.Delete");
		menuBar.removeMenu("SelectedMenu.View");
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void otherActions(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		String action = se.getRequest().getParameter(ACTION);
		if(action.equals(REPLICA_PACCHETTI)) {
			replicaPacchetti();
			callShowGrid(se);
		}else if(action.equals(INVIA_PACCHETTO)) {
			List lstChiaviSelected = SegmentazioneMktgHelper.getChiaviSelected(cadc, se);
			se.getRequest().setAttribute("lstChiaviSelected", lstChiaviSelected);
			se.sendRequest(getServletContext(),"it/softre/thip/archismall/trasmissione/PacchetttoTrasmissioneInvio.jsp", true);
		}else {
			super.otherActions(cadc, se);
		}

	}

	protected void replicaPacchetti() {
		try {
			int ris = PacchetttoTrasmissione.replicaPacchettiFP();
			if(ris < 0) {
				ConnectionManager.rollback();
			}else {
				ConnectionManager.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
	}
}
