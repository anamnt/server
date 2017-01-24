package Controller;

import DTOobjects.Config;
import Endpoints.LoginEndpoint;
import Endpoints.UserEndpoint;
import Endpoints.AdEndpoint;
import Endpoints.BookEndpoint;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.security.KeyStore;

/**
 * Created by alanziberi on 19/10/2016.
 */
public class ServerController {

    public void startServer() throws Exception {
        // Config:
        Config config = new ConfigController().getConfig();
        int srvPort = Integer.parseInt(config.getSrvPort());
        // HTTPS server and SSL context:
        HttpServer httpsServer = HttpServer.create(new InetSocketAddress(srvPort), 0);

        httpsServer.createContext("/login", new LoginEndpoint.LoginHandler());
        httpsServer.createContext("/logout", new LoginEndpoint.LogoutHandler());

        httpsServer.createContext("/getusers", new UserEndpoint.GetUsersHandler());
        httpsServer.createContext("/createuser", new UserEndpoint.CreateUserHandler());
        httpsServer.createContext("/deleteuser", new UserEndpoint.DeleteUserHandler());
        httpsServer.createContext("/deleteuseradmin", new UserEndpoint.DeleteUserAdminHandler());
        httpsServer.createContext("/updateuser", new UserEndpoint.UpdateUserHandler());
        httpsServer.createContext("/updateuseradmin", new UserEndpoint.UpdateUserAdminHandler());

        httpsServer.createContext("/getuserpublic", new UserEndpoint.GetUserPublicHandler());
        httpsServer.createContext("/getuser", new UserEndpoint.GetUserHandler());
        httpsServer.createContext("/getuseradmin", new UserEndpoint.GetUserAdminHandler());

        httpsServer.createContext("/getads", new AdEndpoint.GetAdsHandler());
        httpsServer.createContext("/createad", new AdEndpoint.CreateAdHandler());
        httpsServer.createContext("/deletead", new AdEndpoint.DeleteAdHandler());

        httpsServer.createContext("/getmyads", new AdEndpoint.GetMyAdsHandler());
        httpsServer.createContext("/updatead", new AdEndpoint.UpdateAdHandler());

        httpsServer.createContext("/reservead", new AdEndpoint.ReserveAdHandler());
        httpsServer.createContext("/deletereservation", new AdEndpoint.DeleteReservationHandler());
        httpsServer.createContext("/getmyreservations", new AdEndpoint.GetMyReservationsHandler());
        httpsServer.createContext("/unlockad", new AdEndpoint.UnlockAdHandler());

        httpsServer.createContext("/getadsbook", new AdEndpoint.GetAdsBookHandler());
        httpsServer.createContext("/getadsall", new AdEndpoint.GetAdsAllHandler());
        httpsServer.createContext("/getadsuser", new AdEndpoint.GetAdsUserHandler());

        httpsServer.createContext("/getad", new AdEndpoint.GetAdHandler());
        httpsServer.createContext("/getadpublic", new AdEndpoint.GetAdPublicHandler());

        httpsServer.createContext("/createbook", new BookEndpoint.CreateBookHandler());
        httpsServer.createContext("/getbooks", new BookEndpoint.GetBooksHandler());
        httpsServer.createContext("/deletebook", new BookEndpoint.DeleteBookHandler());

        httpsServer.setExecutor(null);
        httpsServer.start();

        System.out.println("The server is running");
    }
}
