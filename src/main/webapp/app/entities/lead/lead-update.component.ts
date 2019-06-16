import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ILead, Lead } from 'app/shared/model/lead.model';
import { LeadService } from './lead.service';

@Component({
  selector: 'jhi-lead-update',
  templateUrl: './lead-update.component.html'
})
export class LeadUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    leadName: [],
    leadPhone: [],
    leadid: [],
    title: [],
    status: [],
    active: [],
    hotel: [],
    rooms: [],
    contact: [],
    officetel: [],
    cell: [],
    street1: [],
    street2: [],
    city: [],
    state: [],
    postalcode: [],
    country: [],
    website: [],
    email: [],
    followup: [],
    comment: []
  });

  constructor(protected leadService: LeadService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ lead }) => {
      this.updateForm(lead);
    });
  }

  updateForm(lead: ILead) {
    this.editForm.patchValue({
      id: lead.id,
      leadName: lead.leadName,
      leadPhone: lead.leadPhone,
      leadid: lead.leadid,
      title: lead.title,
      status: lead.status,
      active: lead.active,
      hotel: lead.hotel,
      rooms: lead.rooms,
      contact: lead.contact,
      officetel: lead.officetel,
      cell: lead.cell,
      street1: lead.street1,
      street2: lead.street2,
      city: lead.city,
      state: lead.state,
      postalcode: lead.postalcode,
      country: lead.country,
      website: lead.website,
      email: lead.email,
      followup: lead.followup,
      comment: lead.comment
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const lead = this.createFromForm();
    if (lead.id !== undefined) {
      this.subscribeToSaveResponse(this.leadService.update(lead));
    } else {
      this.subscribeToSaveResponse(this.leadService.create(lead));
    }
  }

  private createFromForm(): ILead {
    const entity = {
      ...new Lead(),
      id: this.editForm.get(['id']).value,
      leadName: this.editForm.get(['leadName']).value,
      leadPhone: this.editForm.get(['leadPhone']).value,
      leadid: this.editForm.get(['leadid']).value,
      title: this.editForm.get(['title']).value,
      status: this.editForm.get(['status']).value,
      active: this.editForm.get(['active']).value,
      hotel: this.editForm.get(['hotel']).value,
      rooms: this.editForm.get(['rooms']).value,
      contact: this.editForm.get(['contact']).value,
      officetel: this.editForm.get(['officetel']).value,
      cell: this.editForm.get(['cell']).value,
      street1: this.editForm.get(['street1']).value,
      street2: this.editForm.get(['street2']).value,
      city: this.editForm.get(['city']).value,
      state: this.editForm.get(['state']).value,
      postalcode: this.editForm.get(['postalcode']).value,
      country: this.editForm.get(['country']).value,
      website: this.editForm.get(['website']).value,
      email: this.editForm.get(['email']).value,
      followup: this.editForm.get(['followup']).value,
      comment: this.editForm.get(['comment']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILead>>) {
    result.subscribe((res: HttpResponse<ILead>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
