package it.softre.thip.base.archismall.api;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.thera.thermfw.rs.BaseResource;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 08/07/2024
 * <br><br>
 * <b>71578	DSSOF3	08/07/2024</b>
 * <p>Prima stesura.<br>
 *  
 * </p>
 */

@Path("/archismall")
public class ArchismallResource extends BaseResource {

	private static ArchismallService service = ArchismallService.getInstance();

	@GET
	@Path("/stato-conservazione")
	public Response getStatoConservazionePacchetto(@QueryParam("IdPacchetto") Integer idPacchetto) {
		StatoConservazione statoConservazione = service.statoConservazionePacchetto(idPacchetto);
		if(statoConservazione != null) {
			return Response.ok(statoConservazione).build();
		}else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}


}
