using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;

namespace Ch15_WebAPI.Models
{
    public class Customer
    {
        [Key]
        [StringLength(5)]
        public string CustomerID { get; set; }
        [Required]
        [StringLength(40)]
        public string CompanyName { get; set; }
        [StringLength(30)]
        public string ContactName { get; set; }
        [StringLength(15)]
        public string City { get; set; }
        [StringLength(15)]
        public string Country { get; set; }
        [StringLength(24)]
        public string Phone { get; set; }
    }
}

