import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ILead } from 'app/shared/model/lead.model';
import { AccountService } from 'app/core';
import { LeadService } from './lead.service';

@Component({
  selector: 'jhi-lead',
  templateUrl: './lead.component.html'
})
export class LeadComponent implements OnInit, OnDestroy {
  leads: ILead[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected leadService: LeadService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.leadService
      .query()
      .pipe(
        filter((res: HttpResponse<ILead[]>) => res.ok),
        map((res: HttpResponse<ILead[]>) => res.body)
      )
      .subscribe(
        (res: ILead[]) => {
          this.leads = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInLeads();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ILead) {
    return item.id;
  }

  registerChangeInLeads() {
    this.eventSubscriber = this.eventManager.subscribe('leadListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
