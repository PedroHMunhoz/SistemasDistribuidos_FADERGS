rm *.class # Deleta todos os arquivos compilados (.class) da pasta
javac ServidorRMI.java # Recompila o arquivo do Servidor
javac ClienteRMI.java # Recompila o arquivo do Cliente
rmic ServidorRMI # Gera o STUB do Servidor
rmiregistry & # Executa o rmiregistry sem travar o console
#java ServidorRMI & # Executa o Servidor sem travar o console