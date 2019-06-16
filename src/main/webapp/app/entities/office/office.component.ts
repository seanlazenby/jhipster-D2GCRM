import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IOffice } from 'app/shared/model/office.model';
import { AccountService } from 'app/core';
import { OfficeService } from './office.service';

@Component({
  selector: 'jhi-office',
  templateUrl: './office.component.html'
})
export class OfficeComponent implements OnInit, OnDestroy {
  offices: IOffice[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected officeService: OfficeService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.officeService
      .query()
      .pipe(
        filter((res: HttpResponse<IOffice[]>) => res.ok),
        map((res: HttpResponse<IOffice[]>) => res.body)
      )
      .subscribe(
        (res: IOffice[]) => {
          this.offices = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInOffices();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IOffice) {
    return item.id;
  }

  registerChangeInOffices() {
    this.eventSubscriber = this.eventManager.subscribe('officeListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
