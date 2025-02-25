package examples;

import com.smartystreets.api.ClientBuilder;
import com.smartystreets.api.SharedCredentials;
import com.smartystreets.api.exceptions.SmartyException;
import com.smartystreets.api.international_autocomplete.Client;
import com.smartystreets.api.international_autocomplete.Lookup;
import com.smartystreets.api.international_autocomplete.Candidate;

import java.io.IOException;
import java.util.ArrayList;

public class InternationalAutocompleteExample {
    public static void main(String[] args) throws IOException, SmartyException {
        // We recommend storing your authentication credentials in environment variables.
        SharedCredentials credentials = new SharedCredentials(System.getenv("SMARTY_AUTH_WEB"), System.getenv("SMARTY_AUTH_REFERER"));

        //            The appropriate license values to be used for your subscriptions
        //            can be found on the Subscriptions page of the account dashboard.
        //            https://www.smartystreets.com/docs/cloud/licensing
        ArrayList<String> licenses = new ArrayList<String>();
        licenses.add("international-autocomplete-cloud");
        Client client = new ClientBuilder(credentials).withLicenses(licenses).buildInternationalAutcompleteApiClient();
        Lookup lookup = new Lookup("Louis");

        // Documentation for input fields can be found at:
        // https://smartystreets.com/docs/cloud/international-address-autocomplete-api#pro-http-request-url


        lookup.setCountry("FRA");
        lookup.setLocality("Paris");

        client.send(lookup);

        System.out.println("*** Result ***");
        System.out.println();
        printResult(lookup);
    }

    private static void printResult(Lookup lookup) {
        for (Candidate candidate : lookup.getResult()) {
            System.out.print(candidate.getStreet());
            System.out.print(" ");
            System.out.print(candidate.getLocality());
            System.out.print(" ");
            System.out.print(candidate.getAdministrativeArea());
            System.out.print(", ");
            System.out.print(candidate.getPostalCode());
            System.out.print(", ");
            System.out.println(candidate.getCountryISO3());
        }
    }
}
