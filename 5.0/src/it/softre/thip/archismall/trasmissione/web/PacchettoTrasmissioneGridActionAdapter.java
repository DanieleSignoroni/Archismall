package it.softre.thip.archismall.trasmissione.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.WebMenuBar;
import com.thera.thermfw.web.WebToolBar;
import com.thera.thermfw.web.WebToolBarButton;
import com.thera.thermfw.web.servlet.GridActionAdapter;

import it.softre.thip.archismall.trasmissione.PacchettoInviato;
import it.softre.thip.archismall.trasmissione.PacchettoTrasmissione;

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

public class PacchettoTrasmissioneGridActionAdapter extends GridActionAdapter {
	
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
		//toolBar.removeButton("Delete");
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
				(INVIA_PACCHETTO,
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
		
		WebToolBarButton visualizzaStatoInternal = 
				new WebToolBarButton
				("VIS",
						"action_submit",
						"new",
						"no",
						INVIA_PACCHETTO_RES,
						"VIS",
						INVIA_PACCHETTO_IMG,
						"VIS",
						"single",
						false);
		toolBar.addButton(visualizzaStatoInternal);
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

	@Override
	protected void otherActions(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		String action = se.getRequest().getParameter(ACTION);
		if(action.equals(REPLICA_PACCHETTI)) {
			replicaPacchetti();
			callShowGrid(se);
		}else if(action.equals(INVIA_PACCHETTO)) {
			List<String> lstChiaviSelected = new ArrayList<String>();
			String[] keys = se.getRequest().getParameterValues(GridActionAdapter.OBJECT_KEY);
			for(String key : keys) {
				lstChiaviSelected.add(key);
			}
			se.getRequest().setAttribute("lstChiaviSelected", lstChiaviSelected);
			se.sendRequest(getServletContext(),"it/softre/thip/archismall/trasmissione/PacchettoTrasmissioneInvio.jsp", true);
		}else if(action.equals("VIS")) {
			try {
				PacchettoTrasmissione pacchetto = (PacchettoTrasmissione) 
						PacchettoTrasmissione.elementWithKey(PacchettoTrasmissione.class, se.getRequest().getParameter(OBJECT_KEY), PersistentObject.NO_LOCK);
				if(pacchetto != null) {
					List<PacchettoInviato> pacchettiInviati = pacchetto.pacchettiInviati();
					se.getRequest().setAttribute("rows", pacchettiInviati);
					se.getRequest().setAttribute("Pacchetto", pacchetto);
					se.sendRequest(getServletContext(),"it/softre/thip/archismall/trasmissione/VisualizzaStatoPacchettoInternal.jsp", true);
				}
			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}else {
			super.otherActions(cadc, se);
		}

	}

	protected void replicaPacchetti() {
		try {
			int ris = PacchettoTrasmissione.replicaPacchettiFP();
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
