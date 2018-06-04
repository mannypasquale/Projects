using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Collections.Concurrent;
using Microsoft.EntityFrameworkCore;

namespace Ch15_WebAPI.Models
{
    public class ProductRepository : IProductRepository
    {
        // cache the customers in a thread-safe dictionary 
        // so restarting the service will reset the customers 
        // in real world the repository would perform CRUD 
        // on the database 

        private Northwind db;
        public ProductRepository(Northwind db)
        {
            // load customers from database as a normal 
            // Dictionary with CustomerID is the key,  
            // then convert to a thread-safe 
            // ConcurrentDictionary 
            this.db = db;
        }
        public Product Add(Product p)
        {
            // normalize CustomerID into uppercase 
            // if the customer is new, add it, else 
            // call Update method
            db.Add(p);
            db.SaveChanges();
            return p;
        }

        public Product Find(int id)
        {
            return db.Products.Find(id);
        }
        public IEnumerable<Product> GetAll()
        {
            var prodwithsup = db.Products.Include(x => x.Supplier);
            return prodwithsup;
        }

        public bool Remove(int id)
        {
            Product p = Find(id);
            if (p == null)
                return false;
            db.Products.Remove(p);
            db.SaveChanges();
            return true;
        }
        public Product Update(int id, Product p)
        {
            Product old = Find(id);
            if (old == null)
                return null;
            old.ProductName = p.ProductName;
            old.SupplierID = p.SupplierID;
            //old.Supplier = p.Supplier;
            old.UnitPrice = p.UnitPrice;
            old.UnitsInStock = p.UnitsInStock;
            old.UnitsOnOrder = p.UnitsOnOrder;
            old.ReorderLevel = p.ReorderLevel;
            old.Discontinued = p.Discontinued;

            db.SaveChanges();
            return p;
        }
    }
}

