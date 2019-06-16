import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILead } from 'app/shared/model/lead.model';
import { LeadService } from './lead.service';

@Component({
  selector: 'jhi-lead-delete-dialog',
  templateUrl: './lead-delete-dialog.component.html'
})
export class LeadDeleteDialogComponent {
  lead: ILead;

  constructor(protected leadService: LeadService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.leadService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'leadListModification',
        content: 'Deleted an lead'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-lead-delete-popup',
  template: ''
})
export class LeadDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ lead }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(LeadDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.lead = lead;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/lead', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/lead', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
