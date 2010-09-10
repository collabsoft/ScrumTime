﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using ScrumTime.Models;

namespace ScrumTime.ViewModels
{
    public class ReleaseCollectionViewModel 
    {
        public int ProductId { get; set; }

        public ReleaseCollectionViewModel()
        {
            
        }

        public List<Release> Releases { get; set; }

        public static ReleaseCollectionViewModel BuildByTargetDateDesc(int projectId)
        {
            ScrumTimeEntities scrumTimeEntities = new ScrumTimeEntities();
            ReleaseCollectionViewModel releaseCollectionViewModel = new ReleaseCollectionViewModel();
            Product product = scrumTimeEntities.Products.First<Product>(p => p.ProductId == 1);
            var results = from s in product.Releases
                          orderby s.Target descending
                          select s;
            List<Release> releases = results.ToList<Release>();
            releaseCollectionViewModel.Releases = releases;

            return releaseCollectionViewModel;
        }
    }
}