rm *.class # Deleta todos os arquivos compilados (.class) da pasta
javac ServidorRMIString.java # Recompila o arquivo do Servidor
javac ClienteRMIString.java # Recompila o arquivo do Cliente
rmic ServidorRMIString # Gera o STUB do Servidor
rmiregistry & # Executa o rmiregistry sem travar o console
java ServidorRMIString & # Executa o Servidor sem travar o console