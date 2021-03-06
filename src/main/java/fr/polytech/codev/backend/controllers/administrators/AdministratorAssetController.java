package fr.polytech.codev.backend.controllers.administrators;

import fr.polytech.codev.backend.controllers.AbstractController;
import fr.polytech.codev.backend.exceptions.*;
import fr.polytech.codev.backend.forms.AssetForm;
import fr.polytech.codev.backend.services.impl.AssetServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/cryptowallet/administrator/{token}/asset")
public class AdministratorAssetController extends AbstractController {

    @Autowired
    private AssetServices assetServices;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity all(@PathVariable String token) throws InvalidTokenException, ExpiredTokenException, UnknownEntityException, UnauthorizedUserException {
        assertUserIsAdministrator(token);
        return serializeSuccessResponse(this.assetServices.all());
    }

    @RequestMapping(value = "wallet/{walletId}/cryptocurrency/{cryptocurrencyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity get(@PathVariable String token, @PathVariable int walletId, @PathVariable int cryptocurrencyId) throws InvalidTokenException, ExpiredTokenException, UnknownEntityException, UnauthorizedUserException {
        assertUserIsAdministrator(token);
        return serializeSuccessResponse(this.assetServices.get(walletId, cryptocurrencyId));
    }

    @RequestMapping(value = "wallet/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getByWallet(@PathVariable String token, @PathVariable int id) throws InvalidTokenException, ExpiredTokenException, UnknownEntityException, UnauthorizedUserException {
        assertUserIsAdministrator(token);
        return serializeSuccessResponse(this.assetServices.getByWallet(id));
    }

    @RequestMapping(value = "cryptocurrency/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getByCryptocurrency(@PathVariable String token, @PathVariable int id) throws InvalidTokenException, ExpiredTokenException, UnknownEntityException, UnauthorizedUserException {
        assertUserIsAdministrator(token);
        return serializeSuccessResponse(this.assetServices.getByCryptocurrency(id));
    }

    @RequestMapping(value = "wallet/{walletId}/cryptocurrency/{cryptocurrencyId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity insert(@PathVariable String token, @PathVariable int walletId, @PathVariable int cryptocurrencyId, @RequestBody String data) throws InvalidTokenException, InvalidEntityException, ExpiredTokenException, UnknownEntityException, UnauthorizedUserException {
        assertUserIsAdministrator(token);
        return serializeSuccessResponse("The asset was successfully inserted!", this.assetServices.insert(walletId, cryptocurrencyId, deserialize(data, AssetForm.class)));
    }

    @RequestMapping(value = "wallet/{walletId}/cryptocurrency/{cryptocurrencyId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity update(@PathVariable String token, @PathVariable int walletId, @PathVariable int cryptocurrencyId, @RequestBody String data) throws InvalidTokenException, InvalidEntityException, ExpiredTokenException, UnknownEntityException, UnauthorizedUserException {
        assertUserIsAdministrator(token);
        return serializeSuccessResponse("The asset was successfully updated!", this.assetServices.update(walletId, cryptocurrencyId, deserialize(data, AssetForm.class)));
    }

    @RequestMapping(value = "wallet/{walletId}/cryptocurrency/{cryptocurrencyId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(@PathVariable String token, @PathVariable int walletId, @PathVariable int cryptocurrencyId) throws InvalidTokenException, ExpiredTokenException, UnknownEntityException, UnauthorizedUserException {
        assertUserIsAdministrator(token);
        this.assetServices.delete(walletId, cryptocurrencyId);
        return serializeSuccessResponse("The asset was successfully deleted!");
    }
}