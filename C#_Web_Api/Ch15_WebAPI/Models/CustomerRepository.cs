using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Collections.Concurrent;

namespace Ch15_WebAPI.Models
{
    public class CustomerRepository : ICustomerRepository
    {
        // cache the customers in a thread-safe dictionary 
        // so restarting the service will reset the customers 
        // in real world the repository would perform CRUD 
        // on the database 
        private Northwind db;
        public CustomerRepository(Northwind db)
        {
            // load customers from database as a normal 
            // Dictionary with CustomerID is the key,  
            // then convert to a thread-safe 
            // ConcurrentDictionary 
            this.db = db;
        }
        public Customer Add(Customer c)
        {
            // normalize CustomerID into uppercase 
            c.CustomerID.ToUpper();
            // if the customer is new, add it, else 
            // call Update method
            db.Add(c);
            db.SaveChanges();
            return c;
        }

        public Customer Find(string id)
        {
            return db.Customers.Find(id.ToUpper());
        }
        public IEnumerable<Customer> GetAll()
        {
            return db.Customers;
        }

        public bool Remove(string id)
        {
            Customer c = Find(id);
            if (c == null)
                return false;
            db.Customers.Remove(c);
            db.SaveChanges();
            return true;
        }
        public Customer Update(string id, Customer c)
        {
            Customer old = Find(id);
            if (old == null)
                return null;
            old.CompanyName = c.CompanyName;
            old.City = c.City;
            old.ContactName = c.ContactName;
            old.Country = c.Country;
            old.Phone = c.Phone;
            db.SaveChanges();
            return c;
        }
    }
}

