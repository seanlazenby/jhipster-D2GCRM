import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Lead } from 'app/shared/model/lead.model';
import { LeadService } from './lead.service';
import { LeadComponent } from './lead.component';
import { LeadDetailComponent } from './lead-detail.component';
import { LeadUpdateComponent } from './lead-update.component';
import { LeadDeletePopupComponent } from './lead-delete-dialog.component';
import { ILead } from 'app/shared/model/lead.model';

@Injectable({ providedIn: 'root' })
export class LeadResolve implements Resolve<ILead> {
  constructor(private service: LeadService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ILead> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Lead>) => response.ok),
        map((lead: HttpResponse<Lead>) => lead.body)
      );
    }
    return of(new Lead());
  }
}

export const leadRoute: Routes = [
  {
    path: '',
    component: LeadComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Leads'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LeadDetailComponent,
    resolve: {
      lead: LeadResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Leads'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LeadUpdateComponent,
    resolve: {
      lead: LeadResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Leads'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LeadUpdateComponent,
    resolve: {
      lead: LeadResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Leads'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const leadPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: LeadDeletePopupComponent,
    resolve: {
      lead: LeadResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Leads'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
