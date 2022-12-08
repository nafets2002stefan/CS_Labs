#Laboratory work nr. 4

<br/>
<br/>
<br/>

---

<br/>

### Course: Cryptography & Security
### Author: Berestean Stefan

---

<br/>

## Theory:
Hashing is the process of generating a value from a text or a list of numbers using a mathematical function known as a hash function.

A Hash Function is a function that converts a given numeric or alphanumeric key to a small practical integer value. The mapped integer value is used as an index in the hash table. In simple terms, a hash function maps a significant number or string to a small integer that can be used as the index in the hash table.

The pair is of the form (key, value), where for a given key, one can find a value using some kind of a “function” that maps keys to values. The key for a given object can be calculated using a function called a hash function. For example, given an array A, if i is the key, then we can find the value by simply looking up A[i].

<br/>
Increased data security is the primary benefit of asymmetric cryptography. It is the most secure encryption process because users are never required to reveal or share their private keys, thus decreasing the chances of a cybercriminal discovering a user's private key during transmission.



## Objectives:
1. Get familiar with the asymmetric cryptography, stream and block ciphers.


2. Use an appropriate hashing algorithms to store passwords in a local DB.
  1. You can use already implemented algortihms from libraries provided for your language.
  2. The DB choise is up to you, but it can be something simple, like an in memory one.
3. Use an asymmetric cipher to implement a digital signature process for a user message.
  1. Take the user input message.
  2. Preprocess the message, if needed.
  3. Get a digest of it via hashing.
  4. Encrypt it with the chosen cipher.
  5. Perform a digital signature check by comparing the hash of the message with the decrypted one.

---
<br/>

### Hash

#### Implementation:

* In the first step, let's create an DB class which is very easy.
We have a list of strings and 2 methods for adding a password and checking if
it is there.
```
public class DB {
    private final List<String> passwords = new ArrayList<>();

    public void addToDB(String password){
        passwords.add(password);
    }

    public boolean checkDbIfContains(String password){
        return passwords.contains(password);
    }
}
```
* To implement this algorithm,we'll use Message Digest library.
```
  public class SHA384 {
    public String getSecurePassword(String passwordToHash, String salt){
        String generatedPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
            messageDigest.update(salt.getBytes());
            byte[] bytes = messageDigest.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
  
```
* Another method for this class if getSalt.We'll use it to add random data to
the input of a hash function in order to be an unique result.
```
  public String getSalt() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Arrays.toString(salt);
    }
```

<br/>

---


## Conclusion

In this laboratory work we learned what is a hash function.
Learned how to implement them in code, work with sha-384 algorithm.