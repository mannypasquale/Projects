using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Collections.Concurrent;

namespace Ch15_WebAPI.Models
{
    public class SupplierRespository : ISupplierRepository
    {
        private Northwind db;
        public SupplierRespository(Northwind db)
        {
            // load customers from database as a normal 
            // Dictionary with CustomerID is the key,  
            // then convert to a thread-safe 
            // ConcurrentDictionary 
            this.db = db;
        }
        

        public Supplier Find(int id)
        {
            return db.Suppliers.Find(id);
        }
        public IEnumerable<Supplier> GetAll()
        {
            return db.Suppliers;
        }

        
    }
}

