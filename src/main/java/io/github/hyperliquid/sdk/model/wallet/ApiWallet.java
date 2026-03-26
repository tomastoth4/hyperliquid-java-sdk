package io.github.hyperliquid.sdk.model.wallet;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.web3j.crypto.Credentials;

/**
 * API wallet
 **/
@Getter
@Setter
@ToString(exclude = {"apiWalletPrivateKey", "credentials"})
@EqualsAndHashCode(exclude = {"apiWalletPrivateKey", "credentials"})
public class ApiWallet {

    /**
     * Wallet alias default to primary wallet address
     */
    private String alias;

    /**
     * Primary wallet address (Primary Wallet Address)
     */
    private String primaryWalletAddress;

    /**
     * API wallet corresponding private key (used for signing transaction requests)
     */
    private String apiWalletPrivateKey;

    /**
     * Credentials
     **/
    private Credentials credentials;

    /**
     * Constructor
     *
     * @param alias                wallet alias
     * @param primaryWalletAddress primary wallet address
     * @param apiWalletPrivateKey  api wallet private key
     */
    public ApiWallet(String alias, String primaryWalletAddress, String apiWalletPrivateKey) {
        this.alias = alias;
        this.primaryWalletAddress = primaryWalletAddress;
        this.apiWalletPrivateKey = apiWalletPrivateKey;
    }

    public ApiWallet(String primaryWalletAddress, String apiWalletPrivateKey) {
        this.primaryWalletAddress = primaryWalletAddress;
        this.apiWalletPrivateKey = apiWalletPrivateKey;
    }

    public ApiWallet(String privateKey) {
        this.apiWalletPrivateKey = privateKey;
    }
}
