<?php
   $host        = "host = localhost";
   $port        = "port = 5432";
   $dbname      = "dbname = mannys_full_stack";
   $credentials = "user = mannypasquale";

   $db = pg_connect( "$host $port $dbname $credentials"  );
   if(!$db) {
      echo "Error : Unable to open database\n";
   } else {
      
   }
?>
